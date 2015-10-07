package process.sale.prototypes;

import java.lang.Math;

/**
 *
 * @author peterschaedler
 */
public class TaxCalculator {
    private static final float TAX_RATE = 0.06f;
    
    public static float getTax(float subtotal) {
        return (float) Math.round(subtotal * TAX_RATE * 100) / 100;
    }
}
