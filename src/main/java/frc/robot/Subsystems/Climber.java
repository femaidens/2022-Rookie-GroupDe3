// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;
import frc.robot.Robot;
import frc.robot.Subsystems.UltrasonicTest;
import frc.robot.RobotMap;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Subsystems.UltrasonicTest;

/** Add your docs here. */
public class Climber extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public static CANSparkMax leftArmMotor = new CANSparkMax(RobotMap.leftArmMotorPort, MotorType.kBrushless);
  public static CANSparkMax rightArmMotor = new CANSparkMax(RobotMap.rightArmMotorPort, MotorType.kBrushless);
  public static RelativeEncoder leftArmEncoder = leftArmMotor.getEncoder();
  public static RelativeEncoder rightArmEncoder = rightArmMotor.getEncoder();
  public static DoubleSolenoid leftArmPis = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, RobotMap.leftArmPisForwardPort, RobotMap.leftArmPisBackwardPort);
  public static DoubleSolenoid rightArmPis = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, RobotMap.rightArmPisForwardPort, RobotMap.rightArmPisBackwardPort);
  //A = 24.25in in ticks --> max height of arm
  //B = slightly less than small arm height
  //C = some tiny height above current bar

  public void midBar(){
    while (Robot.ultrasonic.getDistance() > 65) {  //align robot using ultrasonic (x distance from the wall, slightly away from bar not directly below )
      Robot.driveTrain.driveStraight(0.5);
    }
    while(leftArmEncoder.getPosition() < A){ //extending arms up above bar AFTER ALIGNED ROBOT
      leftArmMotor.set(0.5);
      rightArmMotor.set(0.5);
    }
    while (Robot.ultrasonic.getDistance() < 70) { //move robot forward until hits bar 
      Robot.driveTrain.driveStraight(-0.5);
    }
    while(leftArmEncoder.getPosition() > B){ //retract arm down to hook big arms and then small arms 
      leftArmMotor.set(-0.5);
      rightArmMotor.set(-0.5);
    }
  }

  public void changeBar(){
    while(leftArmEncoder.getPosition() < C){ //vertically extending 2 rbot arms to unhook
      leftArmMotor.set(0.5);
      rightArmMotor.set(0.5);
    }
    leftArmPis.set(DoubleSolenoid.Value.kForward); 
    rightArmPis.set(DoubleSolenoid.Value.kForward);
    while(leftArmEncoder.getPosition() < A){ //extend behind high bar 
      leftArmMotor.set(0.5);
      rightArmMotor.set(0.5);
    }
    leftArmPis.set(DoubleSolenoid.Value.kReverse); 
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
