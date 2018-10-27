package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
/**
 * This example is designed to show how to identify a target, get the robot's position, and then plan
 * and execute an approach path to the target.
 *
 * This OpMode uses two "utility" classes to abstract (hide) the hardware and navigation GUTs.
 * These are:  Robot_OmniDrive and Robot_Navigation.
 *
 * This LinearOpMode uses basic hardware and nav calls to drive the robot in either manual or auto mode.
 * AutoMode is engaged by pressing and holding the Left Bumper.  Release the Bumper to return to Manual Mode.
 *
 *  *ManualMode* simply uses the joysticks to move the robot in three degrees of freedom.
 *  - Left stick X (translate left and right)
 *  - Left Stick Y (translate forward and backwards)
 *  - Right Stick X (rotate CW and CCW)
 *
 *  *AutoMode* will approach the image target and attempt to reach a position directly in front
 *  of the center line of the image, with a predefined stand-off distance.
 *
 *  To simplify this example, a gyro is NOT used.  Therefore there is no attempt being made to stabilize
 *  strafing motions, or to perform field-centric driving.
 *
 */

@TeleOp(name="OmniDrive Teleop", group="main")
public class OmniDriveTeleop extends LinearOpMode {

    final double TARGET_DISTANCE =  400.0;    // Hold robot's center 400 mm from target

    /* Declare OpMode members. */
    Robot_OmniDrive robot = new Robot_OmniDrive();   // Use Omni-Directional drive system
    //Robot_Navigation    nav      = new Robot_Navigation();  // Use Image Tracking library

    DcMotor liftMotor = null;
    DcMotor lowerArm;
    DcMotor upperArm;

    Servo leftServo = null;
    Servo rightServo = null;
    Servo leftArmServo;
    Servo rightArmServo;
    Servo sensorServo;
    
    final double OPEN_POS = -1;
    final double CLOSED_POS = 1;
    @Override
    public void runOpMode() {

        // Initialize the robot and navigation
        robot.initDrive(this);
        //nav.initVuforia(this, robot);
        
        //robot.left_back.setDirection(DcMotor.Direction.REVERSE);
        //robot.left_front.setDirection(DcMotor.Direction.REVERSE);
        //robot.right_front.setDirection(DcMotor.Direction.REVERSE);
        
        // Activate Vuforia (this takes a few seconds)
        //nav.activateTracking();
        
        liftMotor = hardwareMap.get(DcMotor.class, "lift_motor");
        //lowerArm = hardwareMap.get(DcMotor.class, "lower_arm");
        //upperArm = hardwareMap.get(DcMotor.class, upper_arm");
        
        leftServo = hardwareMap.get(Servo.class, "left_servo");
        rightServo = hardwareMap.get(Servo.class, "right_servo");
        sensorServo = hardwareMap.get(Servo.class, "sensor_servo");
        //leftArmServo = hardwareMap.get(Servo.class, "left_arm");
        //rightArmServo = hardwareMap.get(Servo.class, "right_arm");
        
        leftServo.setPosition(OPEN_POS);
        rightServo.setPosition(-OPEN_POS);
        sensorServo.setPosition(1);

        // Wait for the game to start (driver presses PLAY)
        while (!isStarted()) {
            // Prompt User
            telemetry.addData(">", "Press start");

            // Display any Nav Targets while we wait for the match to start
            //nav.targetsAreVisible();
           // nav.addNavTelemetry();
            telemetry.update();
        }

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            telemetry.addData(">", "Press Left Bumper to track target");

            // auto drive or manual drive?
            // In auto drive, the robot will approach any target it can see and then press against it
            // In manual drive the robot responds to the Joystick.
            // Drive the robot using the joysticks
            robot.manualDrive();
            sensorServo.setPosition(1);
            //dope code
            if (gamepad2.y) {
                liftMotor.setPower(.5);
            } else if (gamepad2.a) {
                liftMotor.setPower(-.5);
            } else {
                liftMotor.setPower(0);
            }
            
            
            if (gamepad2.x) {
                leftServo.setPosition(CLOSED_POS);
                rightServo.setPosition(-CLOSED_POS);
            }
            
            if (gamepad2.b) {
                leftServo.setPosition(OPEN_POS);
                rightServo.setPosition(-OPEN_POS);
            }
            if (gamepad2.right_bumper){
                leftServo.setPosition(.4);
                rightServo.setPosition(.55);
            }
            
            //lowerArm.setPower(gamepad2.right_joystick_y / 2);
            //upperArm.setPower(gamepad2.left_joystick_y / 2);
            
            /*if (gamepad2.left_bumper) {
                leftArmServo.setPosition(OPEN_POS);
                rightArmServo.setPosition(OPEN_POS);
            } else if (gampepad2.right_bumper) {
                leftArmServo.setPosition(0);
                rightArmServo.setPosition(0);
            }*/

            // Build telemetry messages with Navigation Information;
           // nav.addNavTelemetry();

            //  Move the robot according to the pre-determined axis motions
            robot.moveRobot();
            telemetry.update();
        }

        telemetry.addData(">", "Shutting Down. Bye!");
        telemetry.update();
    }
}
