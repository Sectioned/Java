/*Class to count FPS*/
public class FPS{

	private static int startTime;
	private static int endTime;
	private static int frameTimes = 0;
	private static short frames = 0;

	//
	public final static void StartCounter(){
		//Get the start time in milliseconds then casting as an integer
		startTime = (int) System.currentTimeMillis();
	}

	//
	public final static void StopCountPrintFPS(){
		//Get the second time in milliseconds casting it to an integer
		endTime = (int) System.currentTimeMillis();
		//Store the difference of start and end time
		frameTimes += endTime - startTime;
		//Add one to frames
		frames++;
		//If there is a difference of > 1000ms (1second) print out the results
		if(frameTimes >= 1000){
			System.out.println(String.format("FPS : %s", Long.toString(frames)));
			//Reset the counters
			frameTimes = 0;
			frames = 0;
		}
	}

}