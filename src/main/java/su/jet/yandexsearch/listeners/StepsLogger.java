package su.jet.yandexsearch.listeners;

import io.qameta.allure.listener.StepLifecycleListener;
import io.qameta.allure.model.StepResult;
import lombok.extern.log4j.Log4j2;

/**
 * Logging test steps, register as service.
 *
 * @author Pavel_Markin
 */
@Log4j2
public class StepsLogger implements StepLifecycleListener {

    @Override
    public void afterStepStart(StepResult result) {
        log.info("START: {}", result.getName());
    }

    @Override
    public void beforeStepStop(StepResult result) {
        log.info("FINISHED: {}", result.getName());
    }
}

