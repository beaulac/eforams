package ca.beauvais_la.eforams;

public class Generate3dOGL {
    //
    // Static Fields
    //

    public static double[] eqny;

    public static double[] eqnz;

    public static double[] eqnx2;

    public static double[] eqny2;

    public static double[] eqnz2;

    public static float[] lightPos0;

    public static float[] lightColor2;

    public static float[] lightColor1;

    public static float[] lightColor0;

    public static float[] lightPos2;

    public static float[] lightPos1;

    public static double[] eqnx;

    public static float intensity;

    public static boolean light2;

    public static boolean light1;

    public static boolean light0;

    public static double oddalenie = 0.0;

    private static int mOldY;

    public static int currentTex;

    public static int[] texture = new int[GeneracjaSfer3D.MAX_TEXTURES];

    public static float[] rot = new float[]{
            90f,
            180f,
            90f
    };

    public static float[] eye = new float[]{
            0f,
            0f,
            7f
    };

    private static int mButton = -1;

    private static int mOldX;

    //
    // Constructors
    //
    Generate3dOGL() {
        // Note: this type is marked as 'beforefieldinit'.
        double[] array = new double[4];
        array[0] = 1.0;
        Generate3dOGL.eqnx = array;
        array = new double[4];
        array[1] = 1.0;
        Generate3dOGL.eqny = array;
        array = new double[4];
        array[2] = 1.0;
        Generate3dOGL.eqnz = array;
        Generate3dOGL.eqnx2 = new double[]{
                -0.5,
                0.0,
                0.0,
                0.2
        };
        Generate3dOGL.eqny2 = new double[]{
                0.0,
                -0.5,
                0.0,
                0.2
        };
        Generate3dOGL.eqnz2 = new double[]{
                0.0,
                0.0,
                -0.5,
                0.2
        };
        Generate3dOGL.lightPos0 = new float[]{
                -70f,
                20f,
                90f,
                0f
        };
        Generate3dOGL.lightPos1 = new float[]{
                70f,
                30f,
                90f,
                0f
        };
        float[] array2 = new float[4];
        array2[1] = 40f;
        array2[2] = 100f;
        Generate3dOGL.lightPos2 = array2;
        Generate3dOGL.lightColor0 = new float[]{
                0.5f,
                0.5f,
                0.5f,
                1f
        };
        Generate3dOGL.lightColor1 = new float[]{
                0.5f,
                0.5f,
                0.5f,
                1f
        };
        Generate3dOGL.lightColor2 = new float[]{
                0.5f,
                0.5f,
                0.5f,
                1f
        };
    }
}