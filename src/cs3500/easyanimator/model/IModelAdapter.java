package cs3500.easyanimator.model;

import cs3500.easyanimator.provider.model.IAnimation;

/**
 * Interface representing a two-way adapter which adops our model to be provider's model.
 */
public interface IModelAdapter extends IAnimation, AnimatorModel {

  /**
   * Getter to get base model (Our model implementation) from adapter.
   *
   * @return instance of AnimatorModel
   */
  AnimatorModel getBaseModel();
}
