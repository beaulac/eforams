package ca.beauvais_la.eforams;

/**
 * @author alacasse (1/7/17)
 */
public class Parametry3d {
    //
    // Static Fields
    //
    public static boolean path = false;

    public static boolean z_cut2;

    public static boolean z_cut;

    public static boolean y_cut2;

    public static boolean y_cut;

    public static boolean x_cut2;

    public static boolean x_cut;

    //
    // Fields
    //
    public double startTf;

    public double startRadius;

    public double startBeta;

    public double startFi;

    public double startKx;

    public double startKy;

    public double startKz;


    public double centerX;

    public double centerY;


    public int randomKz;

    public double radius = 1.0;

    public int numberOfPoints = 1;

    public ParametryPoints3d[] parametryPoints3d = new ParametryPoints3d[GeneracjaSfer3D.MAX_SPHERES];

    public int randomTf;

    public int randomRadius;

    public int randomBeta;

    public int randomFi;

    public int randomKx;

    public int randomKy;
}

