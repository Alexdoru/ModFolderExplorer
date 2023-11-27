package modexplorer;

import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

public class ModClassVisitor extends ClassVisitor {

    private final String fileName;
    private String classname;

    public ModClassVisitor(String fileName) {
        super(Opcodes.ASM5);
        this.fileName = fileName;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        this.classname = name;
        //if (interfaces != null) {
        //    for (String anInterface : interfaces) {
        //        if (anInterface.endsWith("IMixinConfigPlugin")) {
        //            Main.log(getClassLocation() + " has interface " + anInterface);
        //        }
        //    }
        //}
    }

    //@Override
    //public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
    //    return new ModAnnotationVisitor();
    //}

    //@Override
    //public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
    //    return new ModMethodVisitor(getClassLocation(), name, desc);
    //}

    private String getClassLocation() {
        return this.fileName + "/" + this.classname;
    }

}
