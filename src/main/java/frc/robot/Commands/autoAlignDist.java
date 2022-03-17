// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Subsystems.Drivetrain;
import frc.robot.Subsystems.Limelight;
import frc.robot.Robot;
public class autoAlignDist extends Command {
  private static final double KP = 0.1;
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
  private static double leftSpeed;
  private static double rightSpeed;



  public autoAlignDist(double left, double right) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.drivetrain);
    leftSpeed = left;
    rightSpeed = right;
    SmartDashboard.putNumber("P", 0.000700);
    SmartDashboard.putNumber("I", 0.000001);
    SmartDashboard.putNumber("D", 0.01);
    SmartDashboard.putNumber("IZone", 100.00);
    SmartDashboard.putNumber("FF", 0.000250);
    SmartDashboard.putNumber("OutputRangeL", 0.0);
    SmartDashboard.putNumber("OutputRangeH", 1.0);

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {}

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    previous_error = current_error;
    current_error = Robot.limelight.getTy();
    integral += (current_error+previous_error)/2*(time);
    derivative = (current_error-previous_error)/time;
    adjust = KP*current_error + KI*integral + KD*derivative;
    System.out.println("Ajust:" + adjust);
    if (current_error > min_error){
      adjust += min_command;
    }
    else if (current_error < -min_error){
      adjust -= min_command;
    }
    Robot.drivetrain.drive(leftSpeed + adjust*.01, rightSpeed - adjust*.01);
    System.out.println("working");
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (Robot.limelight.getDistance() < 41.5 || Robot.limelight.getDistance() > 40.5) 
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
  }
}
