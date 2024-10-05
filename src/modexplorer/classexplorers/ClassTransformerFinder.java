package modexplorer.classexplorers;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;
import modexplorer.Main;

public class ClassTransformerFinder extends ClassVisitor implements ClassExplorer {

    private String fileName;
    private String classname;

    public ClassTransformerFinder() {
        super(Opcodes.ASM5);
    }

    public ClassTransformerFinder(String fileName) {
        super(Opcodes.ASM5);
        this.fileName = fileName;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        this.classname = name;
    }

    @Override
    public MethodVisitor visitMethod(int i, String s, String s1, String s2, String[] strings) {
        if (s.equals("transform") && s1.equals("(Ljava/lang/String;Ljava/lang/String;[B)[B")) {
            log();
        }
        return super.visitMethod(i, s, s1, s2, strings);
    }

    @Override
    public void visitClass(ClassReader cr, String fileName) {
        cr.accept(new ClassTransformerFinder(fileName), ClassReader.SKIP_DEBUG);
    }

    @Override
    public void onSearchEnd() {}

    private void log() {
        Main.log(this.fileName + "/" + this.classname);
    }

}
