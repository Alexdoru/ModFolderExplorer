import jdk.internal.org.objectweb.asm.AnnotationVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

public class ModAnnotationVisitor extends AnnotationVisitor {

    public ModAnnotationVisitor() {
        super(Opcodes.ASM5);
    }

    @Override
    public void visit(String name, Object value) {
        System.out.println("visit : name " + name + " value " + value);
    }

    @Override
    public void visitEnum(String name, String desc, String value) {
        System.out.println("visitEnum : name " + name + " desc " + desc + " value " + value);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String name, String desc) {
        System.out.println("visitAnnotation : name " + name + " desc " + desc);
        return new ModAnnotationVisitor();
    }

    @Override
    public AnnotationVisitor visitArray(String name) {
        System.out.println("visitArray : name " + name);
        return new ModAnnotationVisitor();
    }

}
