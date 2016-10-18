public class CylindricalPoint {

    /**
     * The radial radiusDistance ρ is the Euclidean
     * radiusDistance from the z-axis to the point P
     */
    private int radiusDistance;

    /**
     * The angle between the reference direction on the chosen plane
     * and the line from the origin to the projection of P on the plane
     */
    private int phi;

    /**
     * The height z is the signed radiusDistance from the chosen plane to the
     * point P
     */
    private int height;

    public CylindricalPoint(int distance, int phi, int height) {
        this.radiusDistance = distance;
        this.phi = phi;
        this.height = height;
    }

    public int getRadiusDistance() {
        return radiusDistance;
    }

    public int getHeight() {
        return height;
    }

    public int getPhi() {
        return phi;
    }
}
