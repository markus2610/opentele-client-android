package dk.silverbullet.telemed.questionnaire.node;

import com.google.gson.annotations.Expose;
import dk.silverbullet.telemed.questionnaire.Questionnaire;
import dk.silverbullet.telemed.questionnaire.R;
import dk.silverbullet.telemed.questionnaire.element.EditTextElement;
import dk.silverbullet.telemed.questionnaire.element.TextViewElement;
import dk.silverbullet.telemed.questionnaire.element.TwoButtonElement;
import dk.silverbullet.telemed.questionnaire.expression.Variable;
import dk.silverbullet.telemed.questionnaire.expression.VariableLinkFailedException;
import dk.silverbullet.telemed.utils.Util;

import java.util.Map;

public class SaturationWithoutPulseTestDeviceNode extends DeviceNode {

    @SuppressWarnings("unused")
    private static final String TAG = Util.getTag(SaturationWithoutPulseTestDeviceNode.class);

    @Expose
    private Variable<Integer> saturation;

    public SaturationWithoutPulseTestDeviceNode(Questionnaire questionnaire, String nodeName) {
        super(questionnaire, nodeName);
    }

    @Override
    public void enter() {
        clearElements();
        addElement(new TextViewElement(this, Util.getString(R.string.saturation_saturation, questionnaire)));

        addElement(new TextViewElement(this, Util.getString(R.string.saturation_enter_saturation, questionnaire)));
        EditTextElement saturationElement = new EditTextElement(this);
        saturationElement.setOutputVariable(saturation);
        saturationElement.setDecimals(0);
        addElement(saturationElement);

        TwoButtonElement be = new TwoButtonElement(this);
        be.setLeftNextNode(getNextFailNode());
        be.setLeftText(Util.getString(R.string.default_omit, questionnaire));
        be.setRightNextNode(getNextNode());
        be.setRightText(Util.getString(R.string.default_ok, questionnaire));
        addElement(be);

        super.enter();
    }

    @Override
    public void linkVariables(Map<String, Variable<?>> variablePool) throws VariableLinkFailedException {
        super.linkVariables(variablePool);
        saturation = Util.linkVariable(variablePool, saturation);
    }

    @Override
    public void deviceLeave() {
    }
}
