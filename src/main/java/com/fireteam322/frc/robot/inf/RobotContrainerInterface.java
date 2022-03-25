package com.fireteam322.frc.robot.inf;

import edu.wpi.first.wpilibj2.command.Command;

public interface RobotContrainerInterface {
  public void configureButtonBindings();

  public void chooserSetup();

  public Command getAutonomousCommand();
}
