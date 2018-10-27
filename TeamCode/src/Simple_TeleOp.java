package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Ian.Boyer on 10/21/2016.
 */
@TeleOp(name="Ian's Drive Opmode", group="Linear Opmode")
public class Simple_TeleOp extends OpMode {
    DcMotor leftMotor;
    DcMotor rightMotor;
 
//    Servo jim;
    int ant;
    @Override
    public void init() {

        leftMotor  = hardwareMap.dcMotor.get("left"); //Set up the motor names
        rightMotor = hardwareMap.dcMotor.get("right");

    //    jim = hardwareMap.servo.get("servo1");
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop() {
    float leftY = gamepad1.left_stick_y;
        float rightY = gamepad1.right_stick_y;
        float rightt = gamepad1.right_trigger;
        float leftt = gamepad1.left_trigger;
        boolean a = gamepad1.a;
        boolean b = gamepad1.b;
        boolean y = gamepad1.y;
        boolean x = gamepad1.x;
        boolean dpadup = gamepad1.dpad_up;
        // eg: Run wheels in tank mode (note: The joystick goes negative when pushed forwards)
    /*    if(gamepad1.b == true)
        {
            launch.setPower(1);
        } //Code by Chris
        else
        {
            launch.setPower(0);
        }
    //        jim.setPosition((rightt/2 + 0.5) - (leftt/2 + 0.5));

            if(gamepad1.x == true)
            {
                ant = 1;
            }
        if(gamepad1.y == true)
        {
            ant = -1;
        }
        if(gamepad1.y == true&& gamepad1.x == true)
        {
            ant = 0;
        }
       slide.setPower(ant/2);
      */
      if(leftY < .20) { 
      leftMotor.setPower(leftY);
      rightMotor.setPower(rightY);
      }
      else {
          leftMotor.setPower(leftY - .2);
          rightMotor.setPower(rightY);
      }
      //  rightMotor.setPower(rightY);
     //   shootMotor.setPower(rightt - leftt);
    }
}
// Written by Ian Boyer
