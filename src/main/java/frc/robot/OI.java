// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.buttons.*;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Commands.testEncoder;
import frc.robot.*;

/** Add your docs here. */
public class OI {
    Joystick joy = new Joystick(RobotMap.joyPort);
    Button test = new JoystickButton(joy, 1);
    public void bindButton(){
        test.whenPressed(new testEncoder());
    }
} //TEST!
