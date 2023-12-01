//*Author : Agniva G, Lere O
*Constraints: Angles, compass inaccuracy, angular falloff
*What it can do: Approach a fall head on or a slight angle,
*try to follow a certain compass angle 
*What it can't: Approach a fall with a major angle, follow distances with compass ACCURACY
*In short, this code is hardware limited.
*/
import java.util.Scanner;
public class newBot
{
    public static void main(String args[]){
        Finch bird = new Finch();
        Scanner input = new Scanner(System.in);
        int distance = bird.getDistance();
        System.out.println("What compass angle do you want? ");
        int angle = input.nextInt();
        if((angle < 11) || (angle > 349)){
            System.out.println("This angle doesn't work! Try setting an angle above 10 and below 350");
            angle = input.nextInt();
        }
        System.out.println("Current Compass heading: " + bird.getCompass());
        System.out.println("Distance to nearest obstacle = " + distance);
        //gets distance to nearest obstacle
        if(check(bird, angle) == true){
            start(bird, distance); //move the bird
        }
        else{ //if check returns false, the bot isn't detecting a surface
            bird.disconnect();
            System.exit(0);
        }
      while(bird.getCompass()!=angle-10){
        bird.setMotors(2,-1);
        }
      bird.stop();
      if(bird.getDistance() < 12){
          System.out.println("Permanent object detected");
          System.exit(0);
        }
        else{
            distance = bird.getDistance();
            start(bird, distance);
        }
        //IF THE SENSORS -50 and +50 of angle result in distance less than 10, stop the bot
        //its a wall
    }
    public static boolean check(Finch bird, int angle){
        boolean state = true;
        if(bird.getLine("R") - 8 < 30 || bird.getLine("L") - 8 < 30){ //checks if bot is placed correctly
            System.out.println("This bot hasn't been placed correctly. Please try again. System stopping...");
            return state = false;
        }
        else if(bird.getDistance() > 250){
        System.out.println("This bot doesn't have a potential stop of 400cm. Please replace the bot with an object within 400 meters.");
        return state = false;  
        } 
        
        else{  
            System.out.println("This bot is ready!");
            System.out.println("Compass heading configuring...");
            while(bird.getCompass() !=angle){
            bird.setMotors(2,-1); //spin to find angle. inaccurate due to hardware constraints
            System.out.println("Compass angle config: " + bird.getCompass());
            }
            bird.stop();
            System.out.println("Configured! Bot will run at " + angle + " degrees");
            
        }
        return state;
    }
    public static void start(Finch bird, int distance){
        int x = 0;
        System.out.println("Starting movement");
        System.out.println("Distance to next obstruction: " + distance);
        for(int i = 0; i < distance-3; i++ ){
            bird.setMove("F", 1, 100);
            if(bird.getLine("R") - 8 < 30 || bird.getLine("L") - 8 < 30){
              System.out.println("Possible fall detected. Stopping compass heading and starting antifall.");
              bird.playNote(75, 1);
                fallAvoidance(bird);
                bird.disconnect();
                break;
            }
        }
        }
    public static void fallAvoidance(Finch bird){
    System.out.println("Detected Right Line: " + bird.getLine("R")); //less than 50 before it falls
    System.out.println("Detected Left Line"+ bird.getLine("L"));
      if(bird.getLine("R") - 8 < 30 || bird.getLine("L") - 8 < 30){
          bird.setMove("B", 10, 10);
        while(bird.getLine("R") - 8 < 30)
        {
          bird.setMotors(-50, -40);
        }
        while(bird.getLine("L") -8<30){
            bird.setMotors(-40, -50);
        }
        bird.setMove("B",10,100);
        bird.stop();
           
        }
    }
}
