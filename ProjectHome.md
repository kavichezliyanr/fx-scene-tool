FxSceneTool is a utility that can be compiled with a JavaFX application to enable the user to browse/inspect the application scenegraph. This tool is essentially a developer tool that would typically not be distributed with the application but only used during development. For this reason it consists of two jars - A plugin jar and a runtime jar. The plugin jar serves only to load the runtime. The idea is that the runtime jar can be excluded from a release. The plugin jar will silently fail to load the scene tool when the runtime jar is not present.

**Building the source**

  * Set JFX\_HOME to point to your JavaFX SDK directory.
  * Check out the code
  * Build the project (requires Maven)

**Using FxSceneTool**

  * Add the two jars (fxsct-rt and fxsct-plugin) to you build/classpath.
  * Load the scene tool plugin from your start() method as shown below.

```

@Override
public void start(final Stage stage) throws Exception {
// ...
// ...    Your code here ...
// ...
stage.show();
SceneToolPlugin.load(stage);
}
```

  * To activate the scene tool press **CTRL+Shift+`** (control, shift, back-tick) while the application is running