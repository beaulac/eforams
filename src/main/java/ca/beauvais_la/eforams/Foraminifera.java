package ca.beauvais_la.eforams;


public class Foraminifera {

    // In the original they just hardcoded 0.01745329
    private static final double PI_DIVIDED_BY_180 = Math.PI / 180.0;
//    private static final double PI_DIVIDED_BY_180 = 0.01745329;


    private static final int NUMBER_OF_CHAMBERS = 8;

    private static final double KX_DEFAULT = 1.0;
    private static final double KY_DEFAULT = 1.0;
    private static final double KZ_DEFAULT = 1.0;

    private static final double RADIUS_DEFAULT = 1.1;


    public static boolean odrywanie3d = true;

    private Parametry3d parametry3d = new Parametry3d();
    public static void main(String[] args) {
        Foraminifera foraminifera = new Foraminifera();


        double tf = -0.5;
        double beta = 0d;
        double phi = 0d;

        VaryingParameters params = new VaryingParameters(tf, beta, phi);


        Chamber3d[] outputChambers = foraminifera.generateChambersFromParameters(params);


        System.out.println(String.format("Tf: %f    Beta: %f    Phi: %f", tf, beta, phi));

        for (int i = 0; i < outputChambers.length; i++) {
            Chamber3d chamber = outputChambers[i];
            System.out.println(String.format("Chamber %d: cx: %f  cy: %f  cz: %f", i, chamber.centerX, chamber.centerY, chamber.centerZ));
        }

    }


    private Chamber3d[] generateChambersFromParameters(VaryingParameters varyingParameters) {

        double tf3d = varyingParameters.tf;
        double beta3d = varyingParameters.beta;
        double phi3d = varyingParameters.phi;

        this.parametry3d.numberOfPoints = NUMBER_OF_CHAMBERS;

        this.parametry3d.randomTf = 0;
        this.parametry3d.randomRadius = 0;
        this.parametry3d.randomBeta = 0;
        this.parametry3d.randomFi = 0;
        this.parametry3d.randomKx = 0;
        this.parametry3d.randomKy = 0;
        this.parametry3d.randomKz = 0;
        this.parametry3d.startTf = tf3d;
        double radius3d = RADIUS_DEFAULT;
        this.parametry3d.startRadius = radius3d;
        this.parametry3d.startBeta = beta3d;
        this.parametry3d.startFi = phi3d;

        this.parametry3d.startKx = KZ_DEFAULT;
        this.parametry3d.startKy = KY_DEFAULT;
        this.parametry3d.startKz = KX_DEFAULT;

        this.parametry3d.parametryPoints3d = new ParametryPoints3d[GeneracjaSfer3D.MAX_SPHERES];
        this.parametry3d.parametryPoints3d[0] = new ParametryPoints3d();
        this.parametry3d.parametryPoints3d[0].kx = 1.0;
        this.parametry3d.parametryPoints3d[0].ky = 1.0;
        this.parametry3d.parametryPoints3d[0].kz = 1.0;
        this.parametry3d.parametryPoints3d[0].div_vector_length = tf3d;
        this.parametry3d.parametryPoints3d[0].alphaStopien = beta3d;
        this.parametry3d.parametryPoints3d[0].alpha = this.parametry3d.parametryPoints3d[0].alphaStopien * PI_DIVIDED_BY_180;
        this.parametry3d.parametryPoints3d[0].gammaStopien = phi3d;
        this.parametry3d.parametryPoints3d[0].gamma = this.parametry3d.parametryPoints3d[0].gammaStopien * PI_DIVIDED_BY_180;


        final Chamber3d[] chambers = new Chamber3d[GeneracjaSfer3D.MAX_SPHERES];
        chambers[0] = GeneracjaSfer3D.prepare1st(this.parametry3d.parametryPoints3d[0].ky, this.parametry3d);

        for (int i = 1; i < GeneracjaSfer3D.MAX_SPHERES; i++) {
            final ParametryPoints3d points = new ParametryPoints3d();

            points.kx = KZ_DEFAULT;
            points.ky = KY_DEFAULT;
            points.kz = KX_DEFAULT;

            points.div_vector_length = tf3d;

            points.alphaStopien = beta3d;
            points.alpha = points.alphaStopien * PI_DIVIDED_BY_180;

            points.gammaStopien = phi3d;

            points.gamma = points.gammaStopien * PI_DIVIDED_BY_180;

            points.div_radius_length = radius3d;

            this.parametry3d.parametryPoints3d[i] = points;
            GeneracjaSfer3D.tworzSfere(i, chambers, this.parametry3d.parametryPoints3d, this.parametry3d);
        }

        return chambers;
    }


}