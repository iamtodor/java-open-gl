public class Converter {

    public static CylindricalPoint convert(Point3D point) {
        int radius = (int) Math.sqrt(Math.pow(point.getX(), 2) + Math.pow(point.getY(), 2));
        int phi = (int) Math.atan(point.getY() / point.getX());
        int z = point.getZ();
        return new CylindricalPoint(radius, phi, z);
    }
}
