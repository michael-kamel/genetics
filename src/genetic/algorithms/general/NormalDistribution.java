package genetic.algorithms.general;

public class NormalDistribution 
{
	public static double calculateNormalValue(int val, int shift, double calibration)
	{
		double pow = val-shift;
		pow *= pow;
		pow = -pow;
		pow /= calibration;
		double ret = Math.pow(Math.E, pow);
		return ret;
	}
}
