package modexplorer.classexplorers;

import jdk.internal.org.objectweb.asm.ClassReader;

public interface ClassExplorer {

    void visitClass(ClassReader cr, String fileName);

    void onSearchEnd();

}
