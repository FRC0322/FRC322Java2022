/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.fireteam322.frc.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.fireteam322.frc.robot.Constants;

public class AddressableLEDs extends SubsystemBase {
	private final AddressableLED m_LED;
	private final AddressableLEDBuffer m_LEDBuffer;
	private final int LED_PORT, LED_LENGTH;
	private Color m_ledColor;
	private int m_rainbowFirstPixelHue;
	private double m_timer, m_blinkRate;

	/**
	 * Creates a new AddressableLEDs.
	 */
	public AddressableLEDs(int port, int length) {
		super();
		LED_PORT = port;
		LED_LENGTH = length;
		m_timer = 0.0;
		m_rainbowFirstPixelHue = 0;
		m_blinkRate = 1.0;

		m_LED = new AddressableLED(LED_PORT);
		m_LED.setLength(LED_LENGTH);
		m_LEDBuffer = new AddressableLEDBuffer(LED_LENGTH);
	}

	public Color getLED(int index) {
		return m_LEDBuffer.getLED(index);
	}

	public Color8Bit getLED8Bit(int index) {
		return m_LEDBuffer.getLED8Bit(index);
	}

	public int getLength() {
		return m_LEDBuffer.getLength();
	}

	public void setLED(int index, Color color) {
		m_LEDBuffer.setLED(index, color);
		this.setData();
	}

	public void setLED(int index, Color8Bit color) {
		m_LEDBuffer.setLED(index, color);
		this.setData();
	}

	public void setLEDRGB(int index, int r, int g, int b) {
		m_LEDBuffer.setRGB(index, r, g, b);
		this.setData();
	}

	public void setLEDHSV(int index, int h, int s, int v) {
		m_LEDBuffer.setHSV(index, h, s, v);
		this.setData();
	}

	private void setData() {
		m_LED.setData(m_LEDBuffer);
	}

	public void automaticLEDSetter() {
		if (DriverStation.isEStopped())
			m_blinkRate = Constants.ESTOP_BLINK_RATE;
		else if (DriverStation.isDisabled())
			m_blinkRate = Constants.DISABLED_BLINK_RATE;
		else if (DriverStation.isAutonomous())
			m_blinkRate = Constants.AUTONOMOUS_BLINK_RATE;
		else if (DriverStation.isTeleop())
			m_blinkRate = Constants.TELOP_BLINK_RATE;
		else
			m_blinkRate = 1.0;

		if (DriverStation.getAlliance() == DriverStation.Alliance.Red) {
			m_ledColor = Color.kFirstRed;
		} else if (DriverStation.getAlliance() == DriverStation.Alliance.Blue) {
			m_ledColor = Color.kFirstBlue;
		} else if (DriverStation.getAlliance() == DriverStation.Alliance.Invalid) {
			m_ledColor = Color.kKhaki;
		} else {
			m_ledColor = Color.kDarkMagenta;
		}

		if (m_blinkRate < 0.05) {
			m_blinkRate = 0.0;
			for (var i = 0; i < this.getLength(); i++) {
				this.setLED(i, m_ledColor);
			}
		} else if (m_timer == 0.0 && m_blinkRate >= 0.05) {
			m_timer = Timer.getFPGATimestamp();
		} else if ((Timer.getFPGATimestamp() < (m_timer + m_blinkRate)) && m_blinkRate >= 0.05) {
			for (var j = 0; j < this.getLength(); j += 2) {
				this.setLED(j, m_ledColor);
			}
			for (var k = 1; k < this.getLength(); k += 2) {
				this.setLED(k, Color.kBlack);
			}
		} else if ((Timer.getFPGATimestamp() >= (m_timer + m_blinkRate)) && m_blinkRate >= 0.05) {
			for (var l = 1; l < this.getLength(); l += 2) {
				this.setLED(l, m_ledColor);
			}
			for (var m = 0; m < this.getLength(); m += 2) {
				this.setLED(m, Color.kBlack);
			}
			m_timer = Timer.getFPGATimestamp();
		} else
			m_timer = 0.0;
	}

	public void rainbowLED() {
		// For every pixel
		for (var i = 0; i < this.getLength(); i++) {
			// Calculate the hue - hue is easier for rainbows because the color
			// shape is a circle so only one value needs to precess
			final var hue = (m_rainbowFirstPixelHue + (i * 180 / m_LEDBuffer.getLength())) % 180;
			// Set the value
			this.setLEDHSV(i, hue, 255, 128);
		}
		// Increase by to make the rainbow "move"
		m_rainbowFirstPixelHue += 3;
		// Check bounds
		m_rainbowFirstPixelHue %= 180;
	}

	public void StartLED() {
		this.setData();
		m_LED.start();
	}

	public void StopLED() {
		m_LED.stop();
	}

	@Override
	public void periodic() {
		// This method will be called once per scheduler run
	}
}
