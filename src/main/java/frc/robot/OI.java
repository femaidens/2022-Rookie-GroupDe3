// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.buttons.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.Commands.*;

/** Add your docs here. */
public class OI {
    public static Joystick joy = new Joystick(RobotMap.joyPort);
    public static Joystick leftJoy = new Joystick(RobotMap.leftJoyPort);
    public static JoystickButton intakeButton = new JoystickButton(leftJoy, 1);
    public static JoystickButton retractLowButton = new JoystickButton(joy, 2);
    public static JoystickButton retractHighButton = new JoystickButton(joy, 5);
    public static JoystickButton extendButton = new JoystickButton(joy, 3);
    
    public void bindButton(){
        intakeButton.whenPressed(new SpinWheel());
        retractHighButton.whenPressed(new RetractHigh());
        retractLowButton.whenPressed(new RetractLow());
        extendButton.whenPressed(new ExtendArm());
    }
}
