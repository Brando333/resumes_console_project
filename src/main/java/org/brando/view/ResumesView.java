package org.brando.view;

import org.brando.controller.ResumeController;
import org.brando.model.Resume;
import org.brando.model.User;

import java.util.List;

public class ResumesView {

    public static void showResumesPanel(User user) {

        ResumeController resumeController = new ResumeController();
        List<Resume> resumes = resumeController.list(user.getId());

    }

    public static void showResumesCreate(User user) {


    }
}
