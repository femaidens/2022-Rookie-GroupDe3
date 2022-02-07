// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.buttons.*;
import frc.robot.Commands.Shoot;
import frc.robot.Commands.shoot2;
import frc.robot.Commands.reset;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Commands.Intake;

/** Add your docs here. */
public class OI {
    public static Joystick leftJoy = new Joystick(RobotMap.leftJoyPort);
    public static Joystick rightJoy = new Joystick(RobotMap.rightJoyPort);
    public static Button shoot = new JoystickButton(leftJoy, RobotMap.shootPort);
    public static Button shoot2 = new JoystickButton(rightJoy, RobotMap.shoot2Port);
    public static Button intakeButton = new JoystickButton(leftJoy, RobotMap.intakeButtonPort);
    public void bindButton(){
        shoot.whenPressed(new Shoot());
        shoot2.toggleWhenPressed(new shoot2());
        shoot2.toggleWhenPressed(new reset());
        intakeButton.whileHeld(new Intake());
        
    }
}
