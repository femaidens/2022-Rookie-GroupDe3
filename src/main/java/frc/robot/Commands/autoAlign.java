// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Subsystems.*;
import frc.robot.Subsystems.Limelight;
import frc.robot.Robot;
import frc.robot.Subsystems.PID;
import frc.robot.Subsystems.TankDrive;

public class autoAlign extends Command {
    private static final double KP = 0.07;
    private static final double KI = 0.01;
    private static final double KD = 0.0;
    public static double speed;
    private static double min_error = 0.1;
    private static double min_command = 0.0;
    static double current_error = 0.0;
    static double previous_error = 0.0;
    static double integral = 0.0; 
    static double derivative = 0.0;
    static double adjust = 0.0;
    static double time = 0.1;
    private static double leftSpeed;
    private static double rightSpeed; //for kermit, the right motor's the one backwards
  public autoAlign(double ls, double rs) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.drivetrain);
    leftSpeed = ls;
    rightSpeed = rs;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {}

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    previous_error = current_error;
    current_error = Robot.limelight.getTx();
    integral += (current_error+previous_error)/2*(time);
    derivative = (current_error-previous_error)/time;
    adjust = KP*current_error + KI*integral + KD*derivative;
    if (current_error > min_error){
      adjust += min_command;
    }
    else if (current_error < -min_error){
      adjust -= min_command;
    }
    Robot.drivetrain.driveStraight(leftSpeed + adjust*.01, rightSpeed - adjust*.01);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }
  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.drivetrain.driveStraight(0.0, 0.0);
  } //runs when toggle button is pressed again
}
