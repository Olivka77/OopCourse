public class MainVector {
    public static void main(String[] args) {
        double[] components = {1, 0, 3.5, 4, 5};

        Vector vector1 = new Vector(10);
        Vector vector2 = new Vector(components);
        Vector vector3 = new Vector(vector1);
        Vector vector4 = new Vector(vector2);
        Vector vector5 = new Vector(9, components);
        Vector vector6 = new Vector(3, components);

        System.out.println(vector1.getSize());
        System.out.println(vector2);
        System.out.println(vector3);
        System.out.println(vector4);
        System.out.println(vector5);
        System.out.println(vector6);

        Vector vector7 = new Vector(vector6.addVector(vector2));
        System.out.println(vector7);

        Vector vector8 = new Vector(vector6.subtractVector(vector4)); // исправить
        System.out.println(vector8);

        Vector vector9 = new Vector(vector4.multiplyByScalar(2)); // исправить
        System.out.println(vector9);

        Vector vector10 = new Vector(vector4.vectorReversal()); // исправить
        System.out.println(vector10.getLength()); // добавить искл кроме 0 +
    }
}
