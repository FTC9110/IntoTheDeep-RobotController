package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class TestSlide extends LinearOpMode {
   @Override
   public void runOpMode() throws InterruptedException {
      // Position of the arm when it's lifted
      int armUpPosition = 0;

      // Position of the arm when it's down
      int armDownPosition = 270;

      // Find a motor in the hardware map named "Arm Motor"
      DcMotor leftRotate = hardwareMap.dcMotor.get("leftRotate");
      DcMotor rightRotate = hardwareMap.dcMotor.get("rightRotate");

      // Reset the motor encoder so that it reads zero ticks
      leftRotate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      rightRotate.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

      // Sets the starting position of the arm to the down position
      leftRotate.setTargetPosition(armDownPosition);
      leftRotate.setMode(DcMotor.RunMode.RUN_TO_POSITION);     
      rightRotate.setTargetPosition(armDownPosition);
      rightRotate.setMode(DcMotor.RunMode.RUN_TO_POSITION);

      waitForStart();

      while (opModeIsActive()) {
            // If the A button is pressed, raise the arm
            if (gamepad1.circle) {
               leftRotate.setTargetPosition(armUpPosition);
               leftRotate.setMode(DcMotor.RunMode.RUN_TO_POSITION);
               leftRotate.setPower(0.5);
               rightRotate.setTargetPosition(armUpPosition);
               rightRotate.setMode(DcMotor.RunMode.RUN_TO_POSITION);
               rightRotate.setPower(0.5);
            }

            // If the B button is pressed, lower the arm
            if (gamepad1.square) {
               leftRotate.setTargetPosition(armDownPosition);
               leftRotate.setMode(DcMotor.RunMode.RUN_TO_POSITION);
               leftRotate.setPower(0.25);
               rightRotate.setTargetPosition(-armDownPosition);
               rightRotate.setMode(DcMotor.RunMode.RUN_TO_POSITION);
               rightRotate.setPower(0.25);
            }

            // Get the current position of the armMotor
            double positionL = leftRotate.getCurrentPosition();
            double positionR = rightRotate.getCurrentPosition();

            // Get the target position of the armMotor
            double desiredPositionL = leftRotate.getTargetPosition();
            double desiredPositionR = rightRotate.getTargetPosition();
            // Show the position of the armMotor on telemetry
            telemetry.addData("Encoder Position", positionL);
            telemetry.addData("Encoder Position", positionR);

            // Show the target position of the armMotor on telemetry
            telemetry.addData("Desired Position", desiredPositionL);

            telemetry.addData("Desired Position", desiredPositionR);
            telemetry.update();
      }
   }
}
