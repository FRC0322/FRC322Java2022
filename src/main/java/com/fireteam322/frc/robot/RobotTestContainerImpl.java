package com.fireteam322.frc.robot;

import com.fireteam322.frc.robot.commands.*;
import com.fireteam322.frc.robot.inf.RobotContrainerInterface;
import com.fireteam322.frc.robot.subsystems.*;
import com.fireteam322.frc.robot.utilities.*;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.CvSink;
import edu.wpi.first.cscore.CvSource;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class RobotTestContainerImpl implements RobotContrainerInterface {

  // Joystick
  private Joystick m_leftJoystick = new Joystick(1);
  private Joystick m_rightJoystick = new Joystick(2);
  private final Chassis m_chassis = new Chassis();
  private UsbCamera m_cCamera;
  /*private VideoSink server;*/
  private Thread m_visionThread;

  // Right Trigger
  private JoystickButton brake = new JoystickButton(m_rightJoystick, 1);
  // Left Trigger
  private JoystickButton shooter = new JoystickButton(m_leftJoystick, 1);

  private JoystickButton feeder = new JoystickButton(m_leftJoystick, 2);

  // Subsystems
  private MotorBuilder runBottomeShooter = new MotorBuilder();

  private void setupDrive() {
    DriveWithJoysticks drive =
        new DriveWithJoysticks(
            () -> m_leftJoystick.getY() * ((m_leftJoystick.getRawAxis(3) * -1) + 1.0) / 2.0,
            () -> m_rightJoystick.getY() * ((m_leftJoystick.getRawAxis(3) * -1) + 1.0) / 2.0,
            m_chassis,
            brake);
    m_chassis.setDefaultCommand(drive);
  }

  private void startCameraThread() {
    m_visionThread =
        new Thread(
            () -> {
              CameraServer.startAutomaticCapture(0);
              m_cCamera.setResolution(640, 480);
              CvSink cvsink = CameraServer.getVideo();
              CvSource outputStream = CameraServer.putVideo("Rectangle", 640, 480);
              Mat mat = new Mat();
              while (!Thread.interrupted()) {
                if (cvsink.grabFrame(mat) == 0) {
                  outputStream.notifyError(cvsink.getError());
                  continue;
                }
                Imgproc.rectangle(
                    mat, new Point(100, 100), new Point(400, 400), new Scalar(255, 255, 255), 5);
                outputStream.putFrame(mat);
              }
            });
    m_visionThread.setDaemon(true);
    m_visionThread.start();
  }

  public RobotTestContainerImpl() {

    /*m_cCamera = CameraServer.startAutomaticCapture(3);
    server = CameraServer.getServer();
    m_cCamera.setConnectionStrategy(ConnectionStrategy.kKeepOpen);*/

    // Run Camera Thread with server and opencv implemented
    // https://docs.wpilib.org/en/stable/docs/software/vision-processing/roborio/using-the-cameraserver-on-the-roborio.html#simple-cameraserver-program
    startCameraThread();
    // Driver Train
    setupDrive();

    // Setup the SendableChooser
    chooserSetup();
    // Configure the button bindings
    configureButtonBindings();
  }

  @Override
  public Command getAutonomousCommand() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void configureButtonBindings() {
    // TODO Auto-generated method stub

    runBottomeShooter
        .setDeviceID(Constants.LEFT_SHOOTER_MOTOR)
        .setSpeed(Constants.SHOOTER_SPEED)
        .setJoyStick(shooter)
        .setButtonActionAndSpeed(MotorBuilder.ACTION_HELD, m_leftJoystick.getRawAxis(3) * -1)
        .setButtonActionAndSpeed(MotorBuilder.ACTION_RELEASED, m_leftJoystick.getRawAxis(3) * -1);
  }

  @Override
  public void chooserSetup() {
    // TODO Auto-generated method stub

  }
}
