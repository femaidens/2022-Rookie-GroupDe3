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

  public void midBar(){
    Ultrasonic.setAutomaticMode(true);
    while (leftUltra.getRangeInches() > 65 && rightUltra.getRangeInches() > 65) {  //align robot using ultrasonic NOTE: what's being compared = distance from wall to bar - some inches
      driveStraight(0.5); //move robot past the mid rung
    }
    while(leftArmEncoder.getPosition() < A){ //extending arms up above bar AFTER ALIGNED ROBOT
      leftArmMotor.set(0.5);
      rightArmMotor.set(0.5);
    }
    while (leftUltra.getRangeInches() < 70 && rightUltra.getRangeInches() < 70) { //move robot forward until hits bar NOTE: what's being compared = distance from wall to bar
      driveStraight(-0.5);
    }
    while(leftArmEncoder.getPosition() > B){ //retract big arms that will then also hook small arms 
      leftArmMotor.set(-0.5); 
      rightArmMotor.set(-0.5);
    }
  }

  public void changeBar(){
    while(leftArmEncoder.getPosition() < C){ //vertically extending 2 big robot arms slight a bit to unhook while the small arms stayed latched
      leftArmMotor.set(0.5);
      rightArmMotor.set(0.5);
    }
    leftArmPis.set(DoubleSolenoid.Value.kForward);  //tilt big arm foward
    rightArmPis.set(DoubleSolenoid.Value.kForward);
    while(leftArmEncoder.getPosition() < A){ //extend behind high bar 
      leftArmMotor.set(0.5);
      rightArmMotor.set(0.5);
    }
    leftArmPis.set(DoubleSolenoid.Value.kReverse); //retracts til big arms bump to the rung
    rightArmPis.set(DoubleSolenoid.Value.kReverse);
    while(leftArmEncoder.getPosition() > B){ //hook four arms onto some higher bar 
      leftArmMotor.set(-0.5);
      rightArmMotor.set(-0.5);
    }
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
