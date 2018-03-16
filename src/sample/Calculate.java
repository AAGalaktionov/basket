package sample;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

abstract class Calculate {

    //Парраметры баскетболиста
    static Pair hieght = new Pair(0.0, 2.0);
    //Кольцо блеаить
    static Pair ring1 = new Pair(4.0, 3.05);
    static Pair ring2 = new Pair(4.45, 3.05);
    static double rOfBall = 0.12;
    static boolean isGoal = false;

    static boolean checkFail(double x, double y) {
        double t1 = Math.sqrt(Math.pow(ring1.x - x, 2) + Math.pow(ring1.y - y, 2));
        double t2 = Math.sqrt(Math.pow(ring2.x - x, 2) + Math.pow(ring2.y - y, 2));
        if (t1 <= rOfBall || t2 <= rOfBall || x > ring2.x) return true;
        return false;
    }

    static void cheackGoal(double x, double y1, double y2) {
        if (x > ring1.x && x < ring2.x && y1 >= ring1.y && y2 < ring1.y)
            isGoal = true;
    }


    static double h = 0.05;

    static Map<Double, Double> calculateTraectory(Double tmpV0, Double tmpAngle) {
        ArrayList<Double> tmpVx = new ArrayList<>();
        ArrayList<Double> tmpVy = new ArrayList<>();
        ArrayList<Double> tmpX = new ArrayList<>();
        ArrayList<Double> tmpY = new ArrayList<>();
        Map<Double, Double> list = new TreeMap<>();
        tmpVx.add(tmpV0 * Math.cos(tmpAngle * Math.PI / 180));

        tmpVy.add(tmpV0 * Math.sin(tmpAngle * Math.PI / 180));
        tmpX.add(hieght.x);
        tmpY.add(hieght.y);
        list.put(tmpX.get(0), tmpY.get(0));
        for (int i = 1; i < 100; i++) {
            tmpVx.add(tmpVx.get(i - 1));
            tmpVy.add(tmpVy.get(i - 1) - h * 9.8);
            tmpX.add(tmpX.get(i - 1) + h * tmpVx.get(i));
            tmpY.add(tmpY.get(i - 1) + h * tmpVy.get(i));
            if (tmpY.get(i) > 0 && !checkFail(tmpX.get(i), tmpY.get(i))) {
                if (tmpY.size() > 1)
                    cheackGoal(tmpX.get(i), tmpY.get(i - 1), tmpY.get(i));
                list.put((double) Math.round(tmpX.get(i) * 1000) / 1000, (double) Math.round(tmpY.get(i) * 1000) / 1000);
            }
            else break;
        }

        return list;
    }


    static Map<Double, Double> calculateAnaliticalTraectory(Double tmpV0, Double tmpAngle) {
        ArrayList<Double> antmpX = new ArrayList<>();
        ArrayList<Double> antmpY = new ArrayList<>();
        Map<Double, Double> list = new TreeMap<>();
        antmpX.add(hieght.x);
        antmpY.add(hieght.y);
        list.put(antmpX.get(0), antmpY.get(0));
        double time = 0.0;
        for (int i = 1; i < 100; i++) {
            time += 0.05;
            antmpX.add(antmpX.get(0) + tmpV0 * Math.cos(tmpAngle * Math.PI / 180) * time);
            antmpY.add(antmpY.get(0) + tmpV0 * Math.sin(tmpAngle * Math.PI / 180) * time - 9.8 * time * time / 2);
            if (antmpY.get(i) > 0 && !checkFail(antmpX.get(i), antmpX.get(i))) {

                if (antmpY.size() > 1)
                    cheackGoal(antmpX.get(i), antmpY.get(i - 1), antmpY.get(i));
                /*list.put((double) Math.round(antmpX.get(i)*1000)/1000, (double) Math.round(antmpY.get(i)*1000)/1000);*/
                list.put(antmpX.get(i), antmpY.get(i));
            }
            else break;
        }

        return list;
    }


    static Map<Double, Double> calculateAirResTraectory(Double tmpV0, Double tmpAngle) {
        ArrayList<Double> airtmpVx = new ArrayList<>();
        ArrayList<Double> airtmpVy = new ArrayList<>();
        ArrayList<Double> airtmpX = new ArrayList<>();
        ArrayList<Double> airtmpY = new ArrayList<>();
        Map<Double, Double> list = new TreeMap<>();
        airtmpVx.add(tmpV0 * Math.cos(tmpAngle * Math.PI / 180));

        airtmpVy.add(tmpV0 * Math.sin(tmpAngle * Math.PI / 180));
        airtmpX.add(hieght.x);
        airtmpY.add(hieght.y);
        list.put(airtmpX.get(0), airtmpY.get(0));
        for (int i = 1; i < 100; i++) {
            airtmpVx.add(airtmpVx.get(i - 1) - 6 * Math.PI * 0.0182 * 0.122 * airtmpVx.get(i - 1));
            airtmpVy.add(airtmpVy.get(i - 1) - h * 9.8 - 6 * Math.PI * 0.0182 * 0.122 * airtmpVy.get(i - 1));
            airtmpX.add(airtmpX.get(i - 1) + h * airtmpVx.get(i));
            airtmpY.add(airtmpY.get(i - 1) + h * airtmpVy.get(i));
            if (airtmpY.get(i) > 0 && !checkFail(airtmpX.get(i), airtmpY.get(i))) {
                if (airtmpY.size() > 1)
                    cheackGoal(airtmpX.get(i), airtmpY.get(i - 1), airtmpY.get(i));
                list.put((double) Math.round(airtmpX.get(i) * 1000) / 1000, (double) Math.round(airtmpY.get(i) * 1000) / 1000);
            } else break;
        }

        return list;
    }

}
