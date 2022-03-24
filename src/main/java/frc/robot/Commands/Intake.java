// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;
import frc.robot.Subsystems.IntakeBall;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.*;
import edu.wpi.first.wpilibj.DutyCycleEncoder;


public class Intake extends Command {
  private static final double KP = 0.155;
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
  public Intake() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.intake);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.intake.retract();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    previous_error = current_error;
    current_error = IntakeBall.encoder.getDistance();
    derivative = (current_error-previous_error)/time;
    adjust = KP*current_error + KI*integral + KD*derivative;
    if (current_error > min_error){
      adjust += min_command;
    }
    else if (current_error < -min_error){
      adjust -= min_command;
    }
    Robot.intake.spinWheel(0.7);
    Robot.intake.extend();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  //10.1 + 9.9 = arbritrary #s
  protected boolean isFinished() {
    if (IntakeBall.encoder.getDistance() <= 10.1 && IntakeBall.encoder.getDistance() >= 9.9)
      return true;
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {}

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    IntakeBall.motor1.set(0.0);
    IntakeBall.motor2.set(0.0);
    IntakeBall.encoder.reset();
  }
}
