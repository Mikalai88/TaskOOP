package by.mikhalachkin.electroshop.model.computers;

import by.mikhalachkin.electroshop.model.Appliance;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@ToString
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public abstract class ComputerAppliance extends Appliance {
}

