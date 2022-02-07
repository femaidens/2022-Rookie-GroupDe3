// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.buttons.*;
import frc.robot.Commands.shoot2;
import frc.robot.Commands.reset;
import edu.wpi.first.wpilibj.Joystick;

/** Add your docs here. */
public class OI {
    public static Joystick leftJoy = new Joystick(RobotMap.leftJoyPort);
    public static Joystick rightJoy = new Joystick(RobotMap.rightJoyPort);
    public static Button shoot2 = new JoystickButton(rightJoy, RobotMap.shoot2Port);
    public void bindButton(){
        shoot2.toggleWhenPressed(new shoot2());
        shoot2.toggleWhenPressed(new reset());
        
    }
}
