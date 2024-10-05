package modexplorer;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ModMethodVisitor extends MethodVisitor {

    private final String classLocation;
    private final String methodName;
    private final String desc;

    public ModMethodVisitor(String classLocation, String methodName, String desc) {
        super(Opcodes.ASM9);
        this.classLocation = classLocation;
        this.methodName = methodName;
        this.desc = desc;
    }

    //@Override
    //public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
    //    if (desc.equals("")) {
    //        log();
    //    }
    //    return null;
    //}

    //@Override
    //public void visitTypeInsn(int opcode, String type) {
    //    if (opcode == Opcodes. && type.equals("")) {
    //        log();
    //    }
    //}

    //@Override
    //public void visitLdcInsn(Object cst) {
    //    if (cst instanceof String) {
    //        log();
    //    }
    //}

    //@Override
    //public void visitFieldInsn(int opcode, String owner, String name, String desc) {
    //    if (owner.equals("") && name.equals("")) {
    //        log("field access " + name);
    //    }
    //}

    //@Override
    //public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf) {
    //    if (owner.equals("") && name.equals("")) {
    //        log("calls " + owner + "." + name + ";" + desc);
    //    }
    //}

    private void log() {
        Main.log(this.classLocation + ";" + this.methodName + this.desc);
    }

    private void log(String s) {
        Main.log(this.classLocation + ";" + this.methodName + this.desc + " " + s);
    }

}
