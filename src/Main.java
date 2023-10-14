import jdk.internal.org.objectweb.asm.ClassReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.jar.JarFile;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;

public class Main {

    private static PrintStream printStream;
    private static final Pattern zipJarPattern = Pattern.compile("(.+).(zip|jar)$");
    private static final Pattern classFilePattern = Pattern.compile("[^\\s$]+(\\$\\S+)?\\.class$");

    /**
     * args[0] should be your mods folder, for instance : args[0] = "C:/MultiMC/instances/GTNH/.minecraft/mods"
     */
    public static void main(String[] args) {
        setupLogger();
        final File modFolder = new File(args[0]);
        if (!modFolder.exists()) {
            throw new IllegalStateException("Mod folder path is invalid");
        }
        final List<File> modList = new ArrayList<>();
        fillModList(modFolder, modList);
        System.out.println("Identified " + modList.size() + " .jar files");
        exploreMods(modList);
    }

    private static void setupLogger() {
        try {
            final File logFile = new File(System.getProperty("user.dir") + "/run", "output" + ".txt");
            if (logFile.exists()) {
                //noinspection ResultOfMethodCallIgnored
                logFile.delete();
            }
            if (!logFile.exists()) {
                try {
                    //noinspection ResultOfMethodCallIgnored
                    logFile.createNewFile();
                } catch (IOException e) {
                    throw new RuntimeException("Couldn't create log file");
                }
            }
            printStream = new PrintStream(new FileOutputStream(logFile, true));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Couldn't create log file");
        }
    }

    public static void log(String message) {
        printStream.println(message);
    }

    private static void fillModList(File modFolder, List<File> files) {
        final File[] fList = modFolder.listFiles();
        if (fList != null) {
            for (final File file : fList) {
                if (file.isFile() && zipJarPattern.matcher(file.getName()).matches()) {
                    files.add(file);
                } else if (file.isDirectory()) {
                    fillModList(file, files);
                }
            }
        }
    }

    private static void exploreMods(List<File> modList) {
        final long time = System.currentTimeMillis();
        int classCount = 0;
        for (final File file : modList) {
            try (final JarFile jar = new JarFile(file)) {
                if (file.getName() != null && file.getName().startsWith("forgelin")) {
                    continue;
                }
                for (final ZipEntry ze : Collections.list(jar.entries())) {
                    if (ze.getName() != null && ze.getName().startsWith("__MACOSX")) {
                        continue;
                    }
                    if (classFilePattern.matcher(ze.getName()).matches()) {
                        try {
                            final ClassReader classReader = new ClassReader(jar.getInputStream(ze));
                            classReader.accept(new ModClassVisitor(file.getName()), ClassReader.SKIP_DEBUG);
                            classCount++;
                        } catch (Exception e) {
                            jar.close();
                            System.out.println("There was an error attempting to parse " + file + "/" + ze);
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Error parsing " + file);
            }
        }
        System.out.println("Visited " + classCount + " classes in " + (System.currentTimeMillis() - time) + "ms");
    }

}