package ca.beauvais_la.eforams;

public class GeneracjaSfer3D {
    //
    // Static Fields
    //
    public static int ILOSC_POLUDN = 69;
    public static int ILOSC_ROWNOL = (GeneracjaSfer3D.ILOSC_POLUDN - 1) * 2;
    public static int ILOSC = GeneracjaSfer3D.ILOSC_ROWNOL * GeneracjaSfer3D.ILOSC_POLUDN;

    public static int MAX_SPHERES = 8;

    public static double[] sinusy = new double[GeneracjaSfer3D.ILOSC_ROWNOL];
    public static double[] cosinusy = new double[GeneracjaSfer3D.ILOSC_ROWNOL];

    static {
        wypelnij_cos_i_sin();
    }

    public static void wypelnij_cos_i_sin() {
        double num = Math.acos(-1.0);
        double num2 = num / ((double) GeneracjaSfer3D.ILOSC_ROWNOL * 0.5);
        for (int i = 0; i < GeneracjaSfer3D.ILOSC_ROWNOL; i++) {
            GeneracjaSfer3D.cosinusy[i] = Math.cos((double) i * num2);
            GeneracjaSfer3D.sinusy[i] = Math.sin((double) i * num2);
        }
    }

    public static double thick_ratio = 0.95;

    public static int MAX_TEXTURES = 2;

    private static double ZA_DUZO = 0.75;

    private static double ZA_MALO = 0.1;

    public static float HOLE_RADIUS = 0.05f;


    //
    // Static Methods
    //


    public static int find_hole(int ktory, double x, double y, double z, Chamber3d[] komory, ParametryPoints3d[] parametryKul3d) {
        int result = 0;
        double num = 0.0;
        int num2 = 1;
        for (int i = 0; i < GeneracjaSfer3D.ILOSC; i++) {
            if (komory[ktory].points[i][3] != 0.0) {
                double num3 = Math.sqrt((komory[ktory].points[i][0] - x) * (komory[ktory].points[i][0] - x) + (komory[ktory].points[i][1] - y) * (komory[ktory].points[i][1] - y) + (komory[ktory].points[i][2] - z) * (komory[ktory].points[i][2] - z));
                num3 *= 1.1;
                if (num2 != 0) {
                    result = i;
                    num = num3;
                    num2 = 0;
                }
                if (num3 < num) {
                    if (ktory != 0) {
                        boolean flag = true;
                        for (int j = 0; j < ktory; j++) {
                            double num4 = komory[ktory].points[i][0];
                            double num5 = komory[ktory].points[i][1];
                            double num6 = komory[ktory].points[i][2];
                            double num7 = komory[j].centerX;
                            double num8 = komory[j].centerY;
                            double num9 = komory[j].centerZ;
                            double num10 = parametryKul3d[j].kx * komory[j].radius * (parametryKul3d[j].kx * komory[j].radius);
                            double num11 = parametryKul3d[j].ky * komory[j].radius * (parametryKul3d[j].ky * komory[j].radius);
                            double num12 = parametryKul3d[j].kz * komory[j].radius * (parametryKul3d[j].kz * komory[j].radius);
                            double num13 = (num4 - num7) * (num4 - num7);
                            double num14 = (num5 - num8) * (num5 - num8);
                            double num15 = (num6 - num9) * (num6 - num9);
                            double num16 = num13 / num10;
                            double num17 = num14 / num11;
                            double num18 = num15 / num12;
                            double num19 = num16 + num17 + num18;
                            if (num19 < 1.1) {
                                flag = false;
                            }
                        }
                        if (flag) {
                            result = i;
                            num = num3;
                        }
                    }
                }
            }
        }
        return result;
    }


    private static class PointsOutput {

        final double[][] points;
        final double centerX;
        final double centerY;


        private PointsOutput(double[][] points, double centerX, double centerY) {
            this.points = points;
            this.centerX = centerX;
            this.centerY = centerY;
        }
    }


    public static PointsOutput generatePoints(Chamber3d chamber, int idx, ParametryPoints3d[] parametryPoints) {
        double promien = chamber.radius;
        double centerX = chamber.centerX;
        double centerY = chamber.centerY;
        double centerZ = chamber.centerZ;

        double num = 0.0;
        double num2 = 0.0;
        double num3 = 0.0;
        double num4 = 0.0;
        double num5 = 0.0;

        double num6 = 1.0;
        double num7 = 1.0;
        double num8 = 1.0;
        if (idx > 0) {
            for (int i = 1; i < idx + 1; i++) {
                num6 *= parametryPoints[i].kx;
                num7 *= parametryPoints[i].ky;
                num8 *= parametryPoints[i].kz;
            }
        }

        int num9 = GeneracjaSfer3D.ILOSC_POLUDN * GeneracjaSfer3D.ILOSC_ROWNOL + GeneracjaSfer3D.ILOSC_ROWNOL;

        final double[][] points = new double[num9][4];

        for (int j = 0; j < GeneracjaSfer3D.ILOSC_POLUDN; j++) {
            double num10;
            if (parametryPoints[idx].kx == 1.0 && parametryPoints[idx].ky == 1.0 && parametryPoints[idx].kz == 1.0) {
                num10 = centerY + promien * GeneracjaSfer3D.cosinusy[j];
            } else {
                num10 = centerY + num7 * GeneracjaSfer3D.cosinusy[j];
            }
            for (int k = 0; k < GeneracjaSfer3D.ILOSC_ROWNOL; k++) {
                double num11;
                double num12;
                if (parametryPoints[idx].kx == 1.0 && parametryPoints[idx].ky == 1.0 && parametryPoints[idx].kz == 1.0) {
                    num11 = (points[j * GeneracjaSfer3D.ILOSC_ROWNOL + k][0] = centerX + promien * GeneracjaSfer3D.cosinusy[k] * GeneracjaSfer3D.sinusy[j]);
                    points[j * GeneracjaSfer3D.ILOSC_ROWNOL + k][1] = num10;
                    num12 = (points[j * GeneracjaSfer3D.ILOSC_ROWNOL + k][2] = centerZ + promien * GeneracjaSfer3D.sinusy[k] * GeneracjaSfer3D.sinusy[j]);
                } else {
                    num11 = (points[j * GeneracjaSfer3D.ILOSC_ROWNOL + k][0] = centerX + num6 * GeneracjaSfer3D.cosinusy[k] * GeneracjaSfer3D.sinusy[j]);
                    points[j * GeneracjaSfer3D.ILOSC_ROWNOL + k][1] = num10;
                    num12 = (points[j * GeneracjaSfer3D.ILOSC_ROWNOL + k][2] = centerZ + num8 * GeneracjaSfer3D.sinusy[k] * GeneracjaSfer3D.sinusy[j]);
                }
                points[j * GeneracjaSfer3D.ILOSC_ROWNOL + k][3] = 1.0;
                if (num11 < num3) {
                    num3 = num11;
                }
                if (num11 > num) {
                    num = num11;
                }
                if (num10 < num4) {
                    num4 = num10;
                }
                if (num10 > num2) {
                    num2 = num10;
                }
                if (num12 < num5) {
                    num5 = num12;
                }
            }
        }

        final double newCenterX = (num + num3) / 2.0;
        final double newCenterY = (num2 + num4) / 2.0;
        return new PointsOutput(points, newCenterX, newCenterY);
    }

    public static Chamber3d prepare1st(double ky, Parametry3d parametry3d) {
        Chamber3d chamber = new Chamber3d();
        chamber.centerX = 0f;
        chamber.centerY = 0f;
        chamber.centerZ = 0f;
        chamber.radius = (float) parametry3d.radius;
        chamber.holeX = (float) parametry3d.radius * (float) parametry3d.parametryPoints3d[0].kx;
        chamber.holeY = 0f;
        chamber.holeZ = 0f;
        chamber.vektorTfX = (float) (parametry3d.radius * parametry3d.parametryPoints3d[0].div_vector_length);
        chamber.vektorTfY = 0f;
        chamber.vektorTfZ = 0f;
        chamber.beta = 0f;
        chamber.phi = 0f;

        PointsOutput pointsOutput = GeneracjaSfer3D.generatePoints(chamber, 0, parametry3d.parametryPoints3d);

        parametry3d.centerX = pointsOutput.centerX;
        parametry3d.centerY = pointsOutput.centerY;
        chamber.points = pointsOutput.points;

        return chamber;
    }

    public static void search_hid(int nr, Chamber3d[] sfery, ParametryPoints3d[] parametryKul3d) {
        if (nr != 0) {
            for (int i = 0; i < nr; i++) {
                for (int j = 0; j < GeneracjaSfer3D.ILOSC; j++) {
                    double num = sfery[nr].points[j][0];
                    double num2 = sfery[nr].points[j][1];
                    double num3 = sfery[nr].points[j][2];
                    double num4 = sfery[i].centerX;
                    double num5 = sfery[i].centerY;
                    double num6 = sfery[i].centerZ;
                    double num7;
                    if (parametryKul3d[i].kx != 1.0) {
                        num7 = parametryKul3d[i].kx * parametryKul3d[i].kx;
                    } else {
                        num7 = sfery[i].radius * sfery[i].radius;
                    }
                    double num8;
                    if (parametryKul3d[i].ky != 1.0) {
                        num8 = parametryKul3d[i].ky * parametryKul3d[i].ky;
                    } else {
                        num8 = sfery[i].radius * sfery[i].radius;
                    }
                    double num9 = parametryKul3d[i].kz * sfery[i].radius * (parametryKul3d[i].kz * sfery[i].radius);
                    double num10 = (num - num4) * (num - num4);
                    double num11 = (num2 - num5) * (num2 - num5);
                    double num12 = (num3 - num6) * (num3 - num6);
                    double num13 = num10 / num7;
                    double num14 = num11 / num8;
                    double num15 = num12 / num9;
                    double num16 = num13 + num14 + num15;
                    if (num16 < GeneracjaSfer3D.thick_ratio) {
                        sfery[nr].points[j][3] = 0.0;
                    }
                }
            }
        }
    }

    public static void tworzSfere(int idx, Chamber3d[] chambers, ParametryPoints3d[] parametryKul3d, Parametry3d parametry3d) {
        chambers[idx] = new Chamber3d();
        double lastChamberRadius = chambers[idx - 1].radius;
        double thisChamberRadius = parametryKul3d[idx].div_radius_length * lastChamberRadius;
        double thisChamberTF = thisChamberRadius * parametryKul3d[idx].div_vector_length;

        if (!Foraminifera.odrywanie3d) {
            if (thisChamberRadius < GeneracjaSfer3D.ZA_MALO) {
                thisChamberRadius = GeneracjaSfer3D.ZA_MALO;
            }
            if (Math.abs(thisChamberTF) > GeneracjaSfer3D.ZA_DUZO * thisChamberRadius) {
                thisChamberTF = (double) ((thisChamberTF < 0.0) ? -1 : 1) * GeneracjaSfer3D.ZA_DUZO * thisChamberRadius;
            }
        }
        if (thisChamberTF == 0.0) {
            thisChamberTF = -1E-07;
        }

        double lastChamberHoleX = chambers[idx - 1].holeX;
        double lastChamberHoleY = chambers[idx - 1].holeY;
        double lastChamberHoleZ = chambers[idx - 1].holeZ;

        double twoChambersAgoHoleX;
        double twoChambersAgoHoleY;
        if (idx == 1) {
            twoChambersAgoHoleX = 0.0;
            twoChambersAgoHoleY = 0.0;
        } else {
            twoChambersAgoHoleX = chambers[idx - 2].holeX;
            twoChambersAgoHoleY = chambers[idx - 2].holeY;
            double num9 = chambers[idx - 2].holeZ;
        }

        double refVectorX = lastChamberHoleX - twoChambersAgoHoleX;
        double refVectorY = lastChamberHoleY - twoChambersAgoHoleY;
        double angle = Math.atan2(refVectorY, refVectorX);

        double refPlusPhi = angle + parametry3d.parametryPoints3d[idx].alpha;

        double num12 = chambers[idx - 1].phi + parametry3d.parametryPoints3d[idx].gamma;

        double rotationX = thisChamberTF * Math.cos(refPlusPhi);

        double sinRefPlusPhi = Math.sin(refPlusPhi);
        double rotationY = thisChamberTF * sinRefPlusPhi;
        double rotationZ = thisChamberTF * sinRefPlusPhi * Math.sin(num12);

        double newCenterX = lastChamberHoleX + rotationX;
        double newCenterY = lastChamberHoleY + rotationY;
        double newCenterZ = lastChamberHoleZ + rotationZ;

        chambers[idx].centerX = newCenterX;
        chambers[idx].centerY = newCenterY;
        chambers[idx].centerZ = newCenterZ;

        chambers[idx].radius = thisChamberRadius;
        chambers[idx].vektorTfX = rotationX;
        chambers[idx].vektorTfY = rotationY;
        chambers[idx].vektorTfZ = rotationZ;
        chambers[idx].beta = refPlusPhi;
        chambers[idx].phi = num12;


        PointsOutput pointsOutput = GeneracjaSfer3D.generatePoints(chambers[idx], idx, parametry3d.parametryPoints3d);
        parametry3d.centerX = pointsOutput.centerX;
        parametry3d.centerY = pointsOutput.centerY;
        chambers[idx].points = pointsOutput.points;


        GeneracjaSfer3D.search_hid(idx, chambers, parametryKul3d);
        int num19 = GeneracjaSfer3D.find_hole(idx, lastChamberHoleX, lastChamberHoleY, lastChamberHoleZ, chambers, parametryKul3d);
        chambers[idx].holeX = (float) chambers[idx].points[num19][0];
        chambers[idx].holeY = (float) chambers[idx].points[num19][1];
        chambers[idx].holeZ = (float) chambers[idx].points[num19][2];
    }

}