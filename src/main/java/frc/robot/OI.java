// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.buttons.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.Commands.SpinWheel;
import frc.robot.Commands.RetractArm;

/** Add your docs here. */
public class OI {
    public static Joystick joy = new Joystick(RobotMap.joyPort);
    public static Joystick leftJoy = new Joystick(RobotMap.leftJoyPort);
    public static JoystickButton intakeButton = new JoystickButton(leftJoy, 1);
    public static JoystickButton retractButton = new JoystickButton(joy, 5);
    public void bindButton(){
        intakeButton.whenPressed(new SpinWheel());
        retractButton.whenPressed(new RetractArm());
    }
}
