package net.whg.we.rendering;

import org.joml.Matrix4f;
import net.whg.we.main.Screen;
import net.whg.we.main.Transform3D;

/**
 * The camera is the object in charge of determing the projection and view
 * matrices for how a scene should be rendered.
 */
public class Camera
{
    private final Matrix4f projectionMatrix = new Matrix4f();
    private final Transform3D transform;
    private final Screen screen;
    private float fov = (float) Math.toRadians(90f);
    private float nearClip = 0.1f;
    private float farClip = 1000f;

    /**
     * Creates a new camera object with the default projection matrix.
     * 
     * @param screen
     *     - The screen this camera pulls information from.
     */
    public Camera(Screen screen)
    {
        this(new Transform3D(), screen);
    }

    /**
     * Creates a new camera object with the default projection matrix. The transform
     * for this camera is maintained externally, such as being attached to a game
     * object, and will return the given transform when {@link #getTransform()} is
     * called.
     * 
     * @param transform
     *     - The transform this camera should use.
     * @param screen
     *     - The screen this camera pulls information from.
     */
    public Camera(Transform3D transform, Screen screen)
    {
        this.transform = transform;
        this.screen = screen;
        rebuildProjectionMatrix();
    }

    /**
     * Updates the current projection matrix to match the current settings.
     */
    private void rebuildProjectionMatrix()
    {
        float aspect = screen.getAspect();

        projectionMatrix.identity();
        projectionMatrix.perspective(fov, aspect, nearClip, farClip);
    }

    /**
     * Gets the current projection matrix generated by this camera.
     * 
     * @return The projection matrix.
     */
    public Matrix4f getProjectionMatrix()
    {
        return projectionMatrix;
    }

    /**
     * Gets the current field of view for this camera.
     * 
     * @return The field of view.
     */
    public float getFov()
    {
        return fov;
    }

    /**
     * Assigns a new field of view for this camera.
     * 
     * @param fov
     *     - The new field of view for this camera, in radians.
     */
    public void setFov(float fov)
    {
        this.fov = fov;
        rebuildProjectionMatrix();
    }

    /**
     * Gets the near clipping plane distance.
     * 
     * @return The near clipping plane.
     */
    public float getNearClip()
    {
        return nearClip;
    }

    /**
     * Gets the far clipping place distance.
     * 
     * @return The far clipping plane.
     */
    public float getFarClip()
    {
        return farClip;
    }

    /**
     * Assigns the distances for the clipping planes.
     * 
     * @param near
     *     - The distance to the near clipping plane.
     * @param far
     *     - The distance to the far clipping plane.
     */
    public void setClippingDistance(float near, float far)
    {
        nearClip = near;
        farClip = far;

        rebuildProjectionMatrix();
    }

    /**
     * Gets the transformation object for this camera.
     * 
     * @return The transformation object.
     */
    public Transform3D getTransform()
    {
        return transform;
    }
}
