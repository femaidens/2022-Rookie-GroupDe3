// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.Subsystems.Limelight;

public class shootPID extends Command {
  private static final double KP = .1;
  private static final double KI = 0.0;
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
  private static double zSpeed;

  public shootPID(double zs) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.drivetrain);
    zSpeed = zs;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {}

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (!Limelight.objectSighted()) return;
    previous_error = current_error;
    current_error = Robot.limelight.getTx();
    integral += (current_error+previous_error)/2*(time);
    derivative = (current_error-previous_error)/time;
    adjust = (KP*current_error + KI*integral + KD*derivative)*(-0.1);
    System.out.println("Ajust:" + adjust);
    /*if (current_error > min_error){
      adjust += min_command;
    }
    else if (current_error < -min_error){
      adjust -= min_command;
    }
    */
    Robot.drivetrain.driveC(0, 0, zSpeed + adjust); 
    System.out.println("Emotional damage");
    //QUESTIONABLE, SHOULD ASK. DOES ROBOT MOVE + ALIGN BASED OF LIMELIGHT ERROR 
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
    }

  // Called once after isFinished returns true
  @Override
  protected void end() {}

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.drivetrain.driveC(0.0, 0.0, 0.0);
  }
}
