package modexplorer;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

public class ModClassVisitor extends ClassVisitor {

    private final String fileName;
    private String classname;

    public ModClassVisitor(String fileName) {
        super(Opcodes.ASM9);
        this.fileName = fileName;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        this.classname = name;
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
