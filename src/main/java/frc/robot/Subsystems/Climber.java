// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;
import frc.robot.Robot;
import frc.robot.RobotMap;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Ultrasonic;

/** Add your docs here. */
public class Climber extends Subsystem {
  
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public static CANSparkMax leftArmMotor = new CANSparkMax(RobotMap.leftArmMotorPort, MotorType.kBrushless);
  public static CANSparkMax rightArmMotor = new CANSparkMax(RobotMap.rightArmMotorPort, MotorType.kBrushless);
  public static Ultrasonic leftUltra = new Ultrasonic(RobotMap.leftUltra1Port, RobotMap.leftUltra2Port);
  public static Ultrasonic rightUltra = new Ultrasonic(RobotMap.rightUltra1Port, RobotMap.rightUltra2Port);
  public static RelativeEncoder leftArmEncoder = leftArmMotor.getEncoder();
  public static RelativeEncoder rightArmEncoder = rightArmMotor.getEncoder();
  public static DoubleSolenoid leftArmPis = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, RobotMap.leftArmPisForwardPort, RobotMap.leftArmPisBackwardPort);
  public static DoubleSolenoid rightArmPis = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, RobotMap.rightArmPisForwardPort, RobotMap.rightArmPisBackwardPort);
  
  public static double A = 50; //24.25in in ticks --> max height of big arm's extension
  public static double B = 39; //37in in ticks --> slightly less than small arm height to hook small arm
  public static double C = 27; //2in in ticks --> from top to bottom of the big arm's clipper to unhook big arm
  public static double margin = 0.01;

  /* leftUltra and rightUltra values will never be eqaul thus you want a margin to ensure that the values are at least similar*/
  public void driveStraight(double magnitude) { 
    Ultrasonic.setAutomaticMode(true);
    while (leftUltra.getRangeInches() > rightUltra.getRangeInches() + margin || leftUltra.getRangeInches() > rightUltra.getRangeInches() - margin) {
      Robot.driveTrain.drive(0, magnitude, -0.05); //drivecartesian
    }
    while (rightUltra.getRangeInches() > leftUltra.getRangeInches() + margin || rightUltra.getRangeInches() > leftUltra.getRangeInches() - margin) {
      Robot.driveTrain.drive(0, magnitude, 0.05);
    }
    Robot.driveTrain.drive(0, magnitude, 0); //when all done, move robot along the x-axis according to magnitude
  }

  public void pistIn(){
    leftArmPis.set(DoubleSolenoid.Value.kReverse); 
    rightArmPis.set(DoubleSolenoid.Value.kReverse);
  }
  public void pistOut(){
    leftArmPis.set(DoubleSolenoid.Value.kForward);  
    rightArmPis.set(DoubleSolenoid.Value.kForward);
  }
  public void armExt(){
    leftArmMotor.set(1);
    rightArmMotor.set(1);
  }
  public void armRet(){
    leftArmMotor.set(-1);
    rightArmMotor.set(-1);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
