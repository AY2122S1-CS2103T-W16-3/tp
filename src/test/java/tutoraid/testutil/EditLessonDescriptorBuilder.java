package tutoraid.testutil;

import tutoraid.logic.commands.EditLessonCommand.EditLessonDescriptor;
import tutoraid.model.lesson.Capacity;
import tutoraid.model.lesson.Lesson;
import tutoraid.model.lesson.LessonName;
import tutoraid.model.lesson.Price;
import tutoraid.model.lesson.Remark;

/**
 * A utility class to help with building EditLessonDescriptor objects.
 */
public class EditLessonDescriptorBuilder {

    private final EditLessonDescriptor descriptor;

    public EditLessonDescriptorBuilder() {
        descriptor = new EditLessonDescriptor();
    }

    public EditLessonDescriptorBuilder(EditLessonDescriptor descriptor) {
        this.descriptor = new EditLessonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditLessonDescriptor} with fields containing {@code lesson}'s details
     */
    public EditLessonDescriptorBuilder(Lesson lesson) {
        descriptor = new EditLessonDescriptor();
        descriptor.setLessonName(lesson.getLessonName());
        descriptor.setCapacity(lesson.getCapacity());
        descriptor.setPrice(lesson.getPrice());
        descriptor.setRemark(lesson.getRemark());
    }

    /**
     * Sets the {@code LessonName} of the {@code EditLessonDescriptor} that we are building.
     */
    public EditLessonDescriptorBuilder withLessonName(String name) {
        descriptor.setLessonName(new LessonName(name));
        return this;
    }

    /**
     * Sets the {@code Capacity} of the {@code EditLessonDescriptor} that we are building.
     */
    public EditLessonDescriptorBuilder withCapacity(String capacity) {
        descriptor.setCapacity(new Capacity(capacity));
        return this;
    }

    /**
     * Sets the {@code Price} of the {@code EditLessonDescriptor} that we are building.
     */
    public EditLessonDescriptorBuilder withPrice(String price) {
        descriptor.setPrice(new Price(price));
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code EditLessonDescriptor} that we are building.
     */
    public EditLessonDescriptorBuilder withRemark(String remark) {
        descriptor.setRemark(new Remark(remark));
        return this;
    }

    public EditLessonDescriptor build() {
        return descriptor;
    }
}
