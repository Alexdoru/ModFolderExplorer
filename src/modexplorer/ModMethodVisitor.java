package modexplorer;

import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

public class ModMethodVisitor extends MethodVisitor {

    private final String classLocation;
    private final String methodName;
    private final String desc;

    public ModMethodVisitor(String classLocation, String methodName, String desc) {
        super(Opcodes.ASM5);
        this.classLocation = classLocation;
        this.methodName = methodName;
        this.desc = desc;
    }

    //@Override
    //public AnnotationVisitor visitAnnotation(String s, boolean b) {
    //    if (s.equals("Lcpw/mods/fml/common/eventhandler/SubscribeEvent;")) {
    //        log();
    //    }
    //    return null;
    //}

    //@Override
    //public void visitTypeInsn(int i, String s) {
    //    if (i == Opcodes.NEW && s.equals("org/objectweb/asm/ClassWriter")) {
    //        log();
    //    }
    //}

    //@Override
    //public void visitLdcInsn(Object o) {
    //    if (o instanceof String) {
    //        if (((String) o).contains("battlegear2")) {
    //            log();
    //        }
    //    }
    //}


    //@Override
    //public void visitMethodInsn(int i, String s, String s1, String s2, boolean b) {
    //    if (s.equals("org/objectweb/asm/ClassReader") && s1.equals("accept")) {
    //        log();
    //    }
    //}

    private void log() {
        Main.log(this.classLocation + ";" + this.methodName + this.desc);
    }

}
