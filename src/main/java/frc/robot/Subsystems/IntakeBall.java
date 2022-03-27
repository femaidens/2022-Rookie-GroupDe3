// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.OI;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import frc.robot.RobotMap;

/** Add your docs here. */
public class IntakeBall extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public static CANSparkMax motor1 = new CANSparkMax(RobotMap.intakeMotorPort, MotorType.kBrushless); 
  public static CANSparkMax motor2 = new CANSparkMax(RobotMap.intakeMotor2Port, MotorType.kBrushless); //spinning wheel motor
  public static DutyCycleEncoder encoder =  new DutyCycleEncoder(RobotMap.encoderPort);
//motor2 = base ; spin wheels; motor1 = -exten/retrac

  public void retract(){
    motor1.set(-1);
  }

  public void extend(){
    if (OI.joy.getRawAxis(2) > 0){
      motor1.set(1);
    }
  }
  public void spinWheel(double spd){
    motor2.set(spd);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
