// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.buttons.*;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.*;
import frc.robot.Commands.autoAlign;

/** Add your docs here. */
public class OI {
    public static Joystick Joy = new Joystick(0);
    public static Button alignButton = new JoystickButton(Joy, RobotMap.alignButtonPort);
    public void bindButton(){
        alignButton.toggleWhenPressed(new autoAlign(0.1, 0.103));
    }
}
