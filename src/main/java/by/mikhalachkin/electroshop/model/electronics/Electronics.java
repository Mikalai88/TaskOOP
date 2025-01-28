package by.mikhalachkin.electroshop.model.electronics;

import by.mikhalachkin.electroshop.model.Appliance;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public abstract class Electronics extends Appliance {
  private final boolean supportsWireless; // Поддержка беспроводного подключения
}
