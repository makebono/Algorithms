package com.makebono.algorithms.computationalgeometry.lineintersectiondetection.tools;

import java.math.BigDecimal;

import com.makebono.algorithms.tools.bigdecimalsqrt.BigDecimalSqrt;

/** 
 * @ClassName: Line 
 * @Description: Line object
 * @author makebono
 * @date 2017年11月23日 上午11:10:54 
 *  
 */
public class Line {
    private final BigDecimal x1;
    private final BigDecimal x2;
    private final BigDecimal y1;
    private final BigDecimal y2;
    private BigDecimal k;
    private BigDecimal b;

    public Line(final double x1, final double y1, final double x2, final double y2) {
        this.x1 = new BigDecimal(Double.toString(x1)).setScale(6, BigDecimal.ROUND_HALF_UP);
        this.x2 = new BigDecimal(Double.toString(x2)).setScale(6, BigDecimal.ROUND_HALF_UP);
        this.y1 = new BigDecimal(Double.toString(y1)).setScale(6, BigDecimal.ROUND_HALF_UP);
        this.y2 = new BigDecimal(Double.toString(y2)).setScale(6, BigDecimal.ROUND_HALF_UP);

        this.calculate();
    }

    private void calculate() {
        this.k = (this.y2.subtract(this.y1)).divide(this.x2.subtract(this.x1), 6, BigDecimal.ROUND_HALF_UP);
        this.b = this.y1.subtract(this.x1.multiply(this.k)).setScale(6);
    }

    public boolean isOn(final double x, final double y) {
        final BigDecimal tempx = new BigDecimal(Double.toString(x)).setScale(6, BigDecimal.ROUND_HALF_UP);
        final BigDecimal tempy = new BigDecimal(Double.toString(y)).setScale(6, BigDecimal.ROUND_HALF_UP);
        final BigDecimal right = this.k.multiply(tempx).add(this.b).setScale(6, BigDecimal.ROUND_HALF_UP);
        return tempy.equals(right);
    }

    // Rewrite it later using BigDecimalSqrt.
    public BigDecimal length() {
        if (this.x1.equals(this.x2)) {
            return this.y2.subtract(this.y1).setScale(6);
        } else if (this.y2.equals(this.y1)) {
            return this.x2.subtract(this.x1).setScale(6);
        } else {
            final BigDecimal temp = ((this.x1.subtract(this.x2)).pow(2)).add((this.y1.subtract(this.y2)).pow(2))
                    .setScale(6);
            return BigDecimalSqrt.sqrt(temp, 6);
        }
    }

    public BigDecimal k() {
        return this.k;
    }

    public BigDecimal b() {
        return this.b;
    }

    public BigDecimal YAt(final BigDecimal x) {
        final BigDecimal kx = this.k.multiply(x).setScale(6, BigDecimal.ROUND_HALF_UP);
        final BigDecimal kxb = kx.add(this.b).setScale(6, BigDecimal.ROUND_HALF_UP);
        return kxb;
    }

    public BigDecimal maxX() {
        return (this.x1.compareTo(this.x2)) > 0 ? x1 : x2;
    }

    public BigDecimal minX() {
        return (this.x1.compareTo(this.x2)) < 0 ? x1 : x2;
    }

    public BigDecimal maxXY() {
        return (this.x1.compareTo(this.x2)) > 0 ? y1 : y2;
    }

    public BigDecimal minXY() {
        return (this.x1.compareTo(this.x2)) < 0 ? y1 : y2;
    }
}
