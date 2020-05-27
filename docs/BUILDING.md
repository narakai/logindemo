### 编译gRPC

```
➜  build git:(master) cmake ../..
-- Looking for unistd.h - found
-- Renaming
--     /Users/javayhu/workspace/github/logindemo/tools/grpc/third_party/zlib/zconf.h
-- to 'zconf.h.included' because this file is included with zlib
-- but CMake generates it automatically in the build directory.
-- Configuring done
CMake Warning (dev):
  Policy CMP0042 is not set: MACOSX_RPATH is enabled by default.  Run "cmake
  --help-policy CMP0042" for policy details.  Use the cmake_policy command to
  set the policy and suppress this warning.

  MACOSX_RPATH is not specified for the following targets:

   zlib

This warning is for project developers.  Use -Wno-dev to suppress it.

-- Generating done
-- Build files have been written to: /Users/javayhu/workspace/github/logindemo/tools/grpc/cmake/build
```

```
➜  build git:(master) make
Scanning dependencies of target bssl
[ 98%] Building CXX object third_party/boringssl-with-bazel/CMakeFiles/bssl.dir/src/tool/args.cc.o
[ 98%] Building CXX object third_party/boringssl-with-bazel/CMakeFiles/bssl.dir/src/tool/ciphers.cc.o
[ 98%] Building CXX object third_party/boringssl-with-bazel/CMakeFiles/bssl.dir/src/tool/client.cc.o
[ 98%] Building CXX object third_party/boringssl-with-bazel/CMakeFiles/bssl.dir/src/tool/const.cc.o
[ 98%] Building CXX object third_party/boringssl-with-bazel/CMakeFiles/bssl.dir/src/tool/digest.cc.o
[ 98%] Building CXX object third_party/boringssl-with-bazel/CMakeFiles/bssl.dir/src/tool/file.cc.o
[ 98%] Building CXX object third_party/boringssl-with-bazel/CMakeFiles/bssl.dir/src/tool/generate_ed25519.cc.o
[ 98%] Building CXX object third_party/boringssl-with-bazel/CMakeFiles/bssl.dir/src/tool/genrsa.cc.o
[ 98%] Building CXX object third_party/boringssl-with-bazel/CMakeFiles/bssl.dir/src/tool/pkcs12.cc.o
[ 98%] Building CXX object third_party/boringssl-with-bazel/CMakeFiles/bssl.dir/src/tool/rand.cc.o
[ 99%] Building CXX object third_party/boringssl-with-bazel/CMakeFiles/bssl.dir/src/tool/server.cc.o
[ 99%] Building CXX object third_party/boringssl-with-bazel/CMakeFiles/bssl.dir/src/tool/sign.cc.o
[ 99%] Building CXX object third_party/boringssl-with-bazel/CMakeFiles/bssl.dir/src/tool/speed.cc.o
[ 99%] Building CXX object third_party/boringssl-with-bazel/CMakeFiles/bssl.dir/src/tool/tool.cc.o
[ 99%] Building CXX object third_party/boringssl-with-bazel/CMakeFiles/bssl.dir/src/tool/transport_common.cc.o
[ 99%] Linking CXX executable bssl
[ 99%] Built target bssl
Scanning dependencies of target zlib
[ 99%] Building C object third_party/zlib/CMakeFiles/zlib.dir/adler32.o
[ 99%] Building C object third_party/zlib/CMakeFiles/zlib.dir/compress.o
[ 99%] Building C object third_party/zlib/CMakeFiles/zlib.dir/crc32.o
[ 99%] Building C object third_party/zlib/CMakeFiles/zlib.dir/deflate.o
[ 99%] Building C object third_party/zlib/CMakeFiles/zlib.dir/gzclose.o
[ 99%] Building C object third_party/zlib/CMakeFiles/zlib.dir/gzlib.o
[ 99%] Building C object third_party/zlib/CMakeFiles/zlib.dir/gzread.o
[ 99%] Building C object third_party/zlib/CMakeFiles/zlib.dir/gzwrite.o
[ 99%] Building C object third_party/zlib/CMakeFiles/zlib.dir/inflate.o
[ 99%] Building C object third_party/zlib/CMakeFiles/zlib.dir/infback.o
[ 99%] Building C object third_party/zlib/CMakeFiles/zlib.dir/inftrees.o
[ 99%] Building C object third_party/zlib/CMakeFiles/zlib.dir/inffast.o
[ 99%] Building C object third_party/zlib/CMakeFiles/zlib.dir/trees.o
[ 99%] Building C object third_party/zlib/CMakeFiles/zlib.dir/uncompr.o
[100%] Building C object third_party/zlib/CMakeFiles/zlib.dir/zutil.o
[100%] Linking C shared library libz.dylib
[100%] Built target zlib
Scanning dependencies of target example
[100%] Building C object third_party/zlib/CMakeFiles/example.dir/test/example.o
[100%] Linking C executable example
[100%] Built target example
Scanning dependencies of target minigzip
[100%] Building C object third_party/zlib/CMakeFiles/minigzip.dir/test/minigzip.o
[100%] Linking C executable minigzip
[100%] Built target minigzip
```

### 编译djinni

```
➜  src git:(4f3aa69) ./build
Building Djinni...
Getting org.scala-sbt sbt 0.13.17 ...
downloading https://repo.typesafe.com/typesafe/ivy-releases/org.scala-sbt/sbt/0.13.17/jars/sbt.jar ...
	[SUCCESSFUL ] org.scala-sbt#sbt;0.13.17!sbt.jar (8253ms)
downloading https://repo1.maven.org/maven2/org/scala-lang/scala-library/2.10.7/scala-library-2.10.7.jar ...
	[SUCCESSFUL ] org.scala-lang#scala-library;2.10.7!scala-library.jar (3779ms)
downloading https://repo.typesafe.com/typesafe/ivy-releases/org.scala-sbt/main/0.13.17/jars/main.jar ...
	[SUCCESSFUL ] org.scala-sbt#main;0.13.17!main.jar (10198ms)
downloading https://repo.typesafe.com/typesafe/ivy-releases/org.scala-sbt/compiler-interface/0.13.17/jars/compiler-interface.jar ...
	[SUCCESSFUL ] org.scala-sbt#compiler-interface;0.13.17!compiler-interface.jar (6755ms)
downloading https://repo.typesafe.com/typesafe/ivy-releases/org.scala-sbt/actions/0.13.17/jars/actions.jar ...
	[SUCCESSFUL ] org.scala-sbt#actions;0.13.17!actions.jar (7275ms)
downloading https://repo.typesafe.com/typesafe/ivy-releases/org.scala-sbt/main-settings/0.13.17/jars/main-settings.jar ...
	[SUCCESSFUL ] org.scala-sbt#main-settings;0.13.17!main-settings.jar (6835ms)
downloading https://repo.typesafe.com/typesafe/ivy-releases/org.scala-sbt/interface/0.13.17/jars/interface.jar ...
	[SUCCESSFUL ] org.scala-sbt#interface;0.13.17!interface.jar (5638ms)
downloading https://repo.typesafe.com/typesafe/ivy-releases/org.scala-sbt/io/0.13.17/jars/io.jar ...
	[SUCCESSFUL ] org.scala-sbt#io;0.13.17!io.jar (6488ms)
downloading https://repo.typesafe.com/typesafe/ivy-releases/org.scala-sbt/ivy/0.13.17/jars/ivy.jar ...
	[SUCCESSFUL ] org.scala-sbt#ivy;0.13.17!ivy.jar (7066ms)
downloading https://repo.typesafe.com/typesafe/ivy-releases/org.scala-sbt/logging/0.13.17/jars/logging.jar ...
	[SUCCESSFUL ] org.scala-sbt#logging;0.13.17!logging.jar (5738ms)
downloading https://repo.typesafe.com/typesafe/ivy-releases/org.scala-sbt/logic/0.13.17/jars/logic.jar ...
	[SUCCESSFUL ] org.scala-sbt#logic;0.13.17!logic.jar (5194ms)
downloading https://repo.typesafe.com/typesafe/ivy-releases/org.scala-sbt/process/0.13.17/jars/process.jar ...
	[SUCCESSFUL ] org.scala-sbt#process;0.13.17!process.jar (5700ms)
downloading https://repo.typesafe.com/typesafe/ivy-releases/org.scala-sbt/run/0.13.17/jars/run.jar ...
	[SUCCESSFUL ] org.scala-sbt#run;0.13.17!run.jar (11502ms)
downloading https://repo.typesafe.com/typesafe/ivy-releases/org.scala-sbt/command/0.13.17/jars/command.jar ...
	[SUCCESSFUL ] org.scala-sbt#command;0.13.17!command.jar (9003ms)
downloading https://repo1.maven.org/maven2/org/scala-sbt/launcher-interface/1.0.1/launcher-interface-1.0.1.jar ...
	[SUCCESSFUL ] org.scala-sbt#launcher-interface;1.0.1!launcher-interface.jar (1067ms)
downloading https://repo.typesafe.com/typesafe/ivy-releases/org.scala-sbt/classpath/0.13.17/jars/classpath.jar ...
	[SUCCESSFUL ] org.scala-sbt#classpath;0.13.17!classpath.jar (6918ms)
downloading https://repo.typesafe.com/typesafe/ivy-releases/org.scala-sbt/completion/0.13.17/jars/completion.jar ...
	[SUCCESSFUL ] org.scala-sbt#completion;0.13.17!completion.jar (7789ms)
downloading https://repo.typesafe.com/typesafe/ivy-releases/org.scala-sbt/api/0.13.17/jars/api.jar ...
	[SUCCESSFUL ] org.scala-sbt#api;0.13.17!api.jar (6853ms)
downloading https://repo.typesafe.com/typesafe/ivy-releases/org.scala-sbt/compiler-integration/0.13.17/jars/compiler-integration.jar ...
	[SUCCESSFUL ] org.scala-sbt#compiler-integration;0.13.17!compiler-integration.jar (7392ms)
downloading https://repo.typesafe.com/typesafe/ivy-releases/org.scala-sbt/compiler-ivy-integration/0.13.17/jars/compiler-ivy-integration.jar ...
	[SUCCESSFUL ] org.scala-sbt#compiler-ivy-integration;0.13.17!compiler-ivy-integration.jar (5001ms)
downloading https://repo.typesafe.com/typesafe/ivy-releases/org.scala-sbt/relation/0.13.17/jars/relation.jar ...
	[SUCCESSFUL ] org.scala-sbt#relation;0.13.17!relation.jar (6423ms)
downloading https://repo.typesafe.com/typesafe/ivy-releases/org.scala-sbt/task-system/0.13.17/jars/task-system.jar ...
	[SUCCESSFUL ] org.scala-sbt#task-system;0.13.17!task-system.jar (9572ms)
downloading https://repo.typesafe.com/typesafe/ivy-releases/org.scala-sbt/tasks/0.13.17/jars/tasks.jar ...
	[SUCCESSFUL ] org.scala-sbt#tasks;0.13.17!tasks.jar (5500ms)
downloading https://repo.typesafe.com/typesafe/ivy-releases/org.scala-sbt/tracking/0.13.17/jars/tracking.jar ...
	[SUCCESSFUL ] org.scala-sbt#tracking;0.13.17!tracking.jar (8300ms)
downloading https://repo.typesafe.com/typesafe/ivy-releases/org.scala-sbt/testing/0.13.17/jars/testing.jar ...
	[SUCCESSFUL ] org.scala-sbt#testing;0.13.17!testing.jar (5030ms)
downloading https://repo1.maven.org/maven2/org/scala-lang/scala-compiler/2.10.7/scala-compiler-2.10.7.jar ...
	[SUCCESSFUL ] org.scala-lang#scala-compiler;2.10.7!scala-compiler.jar (10595ms)
downloading https://repo1.maven.org/maven2/org/scala-lang/scala-reflect/2.10.7/scala-reflect-2.10.7.jar ...
	[SUCCESSFUL ] org.scala-lang#scala-reflect;2.10.7!scala-reflect.jar (4591ms)
downloading https://repo.typesafe.com/typesafe/ivy-releases/org.scala-sbt/control/0.13.17/jars/control.jar ...
	[SUCCESSFUL ] org.scala-sbt#control;0.13.17!control.jar (6093ms)
downloading https://repo.typesafe.com/typesafe/ivy-releases/org.scala-sbt/collections/0.13.17/jars/collections.jar ...
	[SUCCESSFUL ] org.scala-sbt#collections;0.13.17!collections.jar (8175ms)
downloading https://repo1.maven.org/maven2/jline/jline/2.14.5/jline-2.14.5.jar ...
	[SUCCESSFUL ] jline#jline;2.14.5!jline.jar (1621ms)
downloading https://repo.typesafe.com/typesafe/ivy-releases/org.scala-sbt/classfile/0.13.17/jars/classfile.jar ...
	[SUCCESSFUL ] org.scala-sbt#classfile;0.13.17!classfile.jar (7814ms)
downloading https://repo.typesafe.com/typesafe/ivy-releases/org.scala-sbt/incremental-compiler/0.13.17/jars/incremental-compiler.jar ...
	[SUCCESSFUL ] org.scala-sbt#incremental-compiler;0.13.17!incremental-compiler.jar (9217ms)
downloading https://repo.typesafe.com/typesafe/ivy-releases/org.scala-sbt/compile/0.13.17/jars/compile.jar ...
	[SUCCESSFUL ] org.scala-sbt#compile;0.13.17!compile.jar (8497ms)
downloading https://repo.typesafe.com/typesafe/ivy-releases/org.scala-sbt/persist/0.13.17/jars/persist.jar ...
	[SUCCESSFUL ] org.scala-sbt#persist;0.13.17!persist.jar (7470ms)
downloading https://repo.typesafe.com/typesafe/ivy-releases/org.scala-tools.sbinary/sbinary_2.10/0.4.2/jars/sbinary_2.10.jar ...
	[SUCCESSFUL ] org.scala-tools.sbinary#sbinary_2.10;0.4.2!sbinary_2.10.jar (6023ms)
downloading https://repo.typesafe.com/typesafe/ivy-releases/org.scala-sbt/cross/0.13.17/jars/cross.jar ...
	[SUCCESSFUL ] org.scala-sbt#cross;0.13.17!cross.jar (8447ms)
downloading https://repo1.maven.org/maven2/org/scala-sbt/ivy/ivy/2.3.0-sbt-b18f59ea3bc914a297bb6f1a4f7fb0ace399e310/ivy-2.3.0-sbt-b18f59ea3bc914a297bb6f1a4f7fb0ace399e310.jar ...
	[SUCCESSFUL ] org.scala-sbt.ivy#ivy;2.3.0-sbt-b18f59ea3bc914a297bb6f1a4f7fb0ace399e310!ivy.jar (2597ms)
downloading https://repo1.maven.org/maven2/com/jcraft/jsch/0.1.50/jsch-0.1.50.jar ...
	[SUCCESSFUL ] com.jcraft#jsch;0.1.50!jsch.jar (831ms)
downloading https://repo1.maven.org/maven2/org/scala-sbt/serialization_2.10/0.1.2/serialization_2.10-0.1.2.jar ...
	[SUCCESSFUL ] org.scala-sbt#serialization_2.10;0.1.2!serialization_2.10.jar (994ms)
downloading https://repo1.maven.org/maven2/org/scala-lang/modules/scala-pickling_2.10/0.10.1/scala-pickling_2.10-0.10.1.jar ...
	[SUCCESSFUL ] org.scala-lang.modules#scala-pickling_2.10;0.10.1!scala-pickling_2.10.jar (2136ms)
downloading https://repo1.maven.org/maven2/org/json4s/json4s-core_2.10/3.2.10/json4s-core_2.10-3.2.10.jar ...
	[SUCCESSFUL ] org.json4s#json4s-core_2.10;3.2.10!json4s-core_2.10.jar (1931ms)
downloading https://repo1.maven.org/maven2/org/spire-math/jawn-parser_2.10/0.6.0/jawn-parser_2.10-0.6.0.jar ...
	[SUCCESSFUL ] org.spire-math#jawn-parser_2.10;0.6.0!jawn-parser_2.10.jar (1967ms)
downloading https://repo1.maven.org/maven2/org/spire-math/json4s-support_2.10/0.6.0/json4s-support_2.10-0.6.0.jar ...
	[SUCCESSFUL ] org.spire-math#json4s-support_2.10;0.6.0!json4s-support_2.10.jar (653ms)
downloading https://repo1.maven.org/maven2/org/scalamacros/quasiquotes_2.10/2.0.1/quasiquotes_2.10-2.0.1.jar ...
	[SUCCESSFUL ] org.scalamacros#quasiquotes_2.10;2.0.1!quasiquotes_2.10.jar (2591ms)
downloading https://repo1.maven.org/maven2/org/json4s/json4s-ast_2.10/3.2.10/json4s-ast_2.10-3.2.10.jar ...
	[SUCCESSFUL ] org.json4s#json4s-ast_2.10;3.2.10!json4s-ast_2.10.jar (809ms)
downloading https://repo1.maven.org/maven2/com/thoughtworks/paranamer/paranamer/2.6/paranamer-2.6.jar ...
	[SUCCESSFUL ] com.thoughtworks.paranamer#paranamer;2.6!paranamer.jar (652ms)
downloading https://repo.typesafe.com/typesafe/ivy-releases/org.scala-sbt/cache/0.13.17/jars/cache.jar ...
	[SUCCESSFUL ] org.scala-sbt#cache;0.13.17!cache.jar (7431ms)
downloading https://repo.typesafe.com/typesafe/ivy-releases/org.scala-sbt/test-agent/0.13.17/jars/test-agent.jar ...
	[SUCCESSFUL ] org.scala-sbt#test-agent;0.13.17!test-agent.jar (8805ms)
downloading https://repo1.maven.org/maven2/org/scala-sbt/test-interface/1.0/test-interface-1.0.jar ...
	[SUCCESSFUL ] org.scala-sbt#test-interface;1.0!test-interface.jar (1151ms)
downloading https://repo.typesafe.com/typesafe/ivy-releases/org.scala-sbt/apply-macro/0.13.17/jars/apply-macro.jar ...
	[SUCCESSFUL ] org.scala-sbt#apply-macro;0.13.17!apply-macro.jar (8796ms)
downloading https://repo1.maven.org/maven2/org/scala-sbt/template-resolver/0.1/template-resolver-0.1.jar ...
	[SUCCESSFUL ] org.scala-sbt#template-resolver;0.1!template-resolver.jar (1043ms)
:: retrieving :: org.scala-sbt#boot-app
	confs: [default]
	49 artifacts copied, 0 already retrieved (17577kB/62ms)
Getting Scala 2.10.7 (for sbt)...
downloading https://repo1.maven.org/maven2/org/scala-lang/jline/2.10.7/jline-2.10.7.jar ...
	[SUCCESSFUL ] org.scala-lang#jline;2.10.7!jline.jar (1322ms)
downloading https://repo1.maven.org/maven2/org/fusesource/jansi/jansi/1.4/jansi-1.4.jar ...
	[SUCCESSFUL ] org.fusesource.jansi#jansi;1.4!jansi.jar (980ms)
:: retrieving :: org.scala-sbt#boot-scala
	confs: [default]
	5 artifacts copied, 0 already retrieved (24560kB/27ms)
[info] Loading project definition from /Users/javayhu/workspace/github/logindemo/djinni/src/project
[info] Updating {file:/Users/javayhu/workspace/github/logindemo/djinni/src/project/}src-build...
[info] Resolving org.fusesource.jansi#jansi;1.4 ...
[info] downloading https://repo.scala-sbt.org/scalasbt/sbt-plugin-releases/com.typesafe.sbt/sbt-start-script/scala_2.10/sbt_0.13/0.10.0/jars/sbt-start-script.jar ...
[info] 	[SUCCESSFUL ] com.typesafe.sbt#sbt-start-script;0.10.0!sbt-start-script.jar (6084ms)
[info] downloading https://repo.scala-sbt.org/scalasbt/sbt-plugin-releases/com.eed3si9n/sbt-assembly/scala_2.10/sbt_0.13/0.14.5/jars/sbt-assembly.jar ...
[info] 	[SUCCESSFUL ] com.eed3si9n#sbt-assembly;0.14.5!sbt-assembly.jar (6557ms)
[info] downloading https://repo1.maven.org/maven2/org/scalactic/scalactic_2.10/3.0.1/scalactic_2.10-3.0.1.jar ...
[info] 	[SUCCESSFUL ] org.scalactic#scalactic_2.10;3.0.1!scalactic_2.10.jar(bundle) (3306ms)
[info] downloading https://repo1.maven.org/maven2/org/pantsbuild/jarjar/1.6.4/jarjar-1.6.4.jar ...
[info] 	[SUCCESSFUL ] org.pantsbuild#jarjar;1.6.4!jarjar.jar (848ms)
[info] downloading https://repo1.maven.org/maven2/org/apache/ant/ant/1.9.9/ant-1.9.9.jar ...
[info] 	[SUCCESSFUL ] org.apache.ant#ant;1.9.9!ant.jar (5748ms)
[info] downloading https://repo1.maven.org/maven2/org/ow2/asm/asm/5.2/asm-5.2.jar ...
[info] 	[SUCCESSFUL ] org.ow2.asm#asm;5.2!asm.jar (677ms)
[info] downloading https://repo1.maven.org/maven2/org/ow2/asm/asm-commons/5.2/asm-commons-5.2.jar ...
[info] 	[SUCCESSFUL ] org.ow2.asm#asm-commons;5.2!asm-commons.jar (676ms)
[info] downloading https://repo1.maven.org/maven2/org/apache/maven/maven-plugin-api/3.3.9/maven-plugin-api-3.3.9.jar ...
[info] 	[SUCCESSFUL ] org.apache.maven#maven-plugin-api;3.3.9!maven-plugin-api.jar (673ms)
[info] downloading https://repo1.maven.org/maven2/org/apache/ant/ant-launcher/1.9.9/ant-launcher-1.9.9.jar ...
[info] 	[SUCCESSFUL ] org.apache.ant#ant-launcher;1.9.9!ant-launcher.jar (1045ms)
[info] downloading https://repo1.maven.org/maven2/org/ow2/asm/asm-tree/5.2/asm-tree-5.2.jar ...
[info] 	[SUCCESSFUL ] org.ow2.asm#asm-tree;5.2!asm-tree.jar (676ms)
[info] downloading https://repo1.maven.org/maven2/org/apache/maven/maven-model/3.3.9/maven-model-3.3.9.jar ...
[info] 	[SUCCESSFUL ] org.apache.maven#maven-model;3.3.9!maven-model.jar (1007ms)
[info] downloading https://repo1.maven.org/maven2/org/apache/maven/maven-artifact/3.3.9/maven-artifact-3.3.9.jar ...
[info] 	[SUCCESSFUL ] org.apache.maven#maven-artifact;3.3.9!maven-artifact.jar (675ms)
[info] downloading https://repo1.maven.org/maven2/org/eclipse/sisu/org.eclipse.sisu.plexus/0.3.2/org.eclipse.sisu.plexus-0.3.2.jar ...
[info] 	[SUCCESSFUL ] org.eclipse.sisu#org.eclipse.sisu.plexus;0.3.2!org.eclipse.sisu.plexus.jar(eclipse-plugin) (1004ms)
[info] downloading https://repo1.maven.org/maven2/org/codehaus/plexus/plexus-utils/3.0.22/plexus-utils-3.0.22.jar ...
[info] 	[SUCCESSFUL ] org.codehaus.plexus#plexus-utils;3.0.22!plexus-utils.jar (1169ms)
[info] downloading https://repo1.maven.org/maven2/org/apache/commons/commons-lang3/3.4/commons-lang3-3.4.jar ...
[info] 	[SUCCESSFUL ] org.apache.commons#commons-lang3;3.4!commons-lang3.jar (1494ms)
[info] downloading https://repo1.maven.org/maven2/javax/enterprise/cdi-api/1.0/cdi-api-1.0.jar ...
[info] 	[SUCCESSFUL ] javax.enterprise#cdi-api;1.0!cdi-api.jar (676ms)
[info] downloading https://repo1.maven.org/maven2/org/eclipse/sisu/org.eclipse.sisu.inject/0.3.2/org.eclipse.sisu.inject-0.3.2.jar ...
[info] 	[SUCCESSFUL ] org.eclipse.sisu#org.eclipse.sisu.inject;0.3.2!org.eclipse.sisu.inject.jar(eclipse-plugin) (1330ms)
[info] downloading https://repo1.maven.org/maven2/org/codehaus/plexus/plexus-component-annotations/1.5.5/plexus-component-annotations-1.5.5.jar ...
[info] 	[SUCCESSFUL ] org.codehaus.plexus#plexus-component-annotations;1.5.5!plexus-component-annotations.jar (672ms)
[info] downloading https://repo1.maven.org/maven2/org/codehaus/plexus/plexus-classworlds/2.5.2/plexus-classworlds-2.5.2.jar ...
[info] 	[SUCCESSFUL ] org.codehaus.plexus#plexus-classworlds;2.5.2!plexus-classworlds.jar(bundle) (831ms)
[info] downloading https://repo1.maven.org/maven2/javax/annotation/jsr250-api/1.0/jsr250-api-1.0.jar ...
[info] 	[SUCCESSFUL ] javax.annotation#jsr250-api;1.0!jsr250-api.jar (667ms)
[info] downloading https://repo1.maven.org/maven2/javax/inject/javax.inject/1/javax.inject-1.jar ...
[info] 	[SUCCESSFUL ] javax.inject#javax.inject;1!javax.inject.jar (670ms)
[info] Done updating.
[info] Set current project to src (in build file:/Users/javayhu/workspace/github/logindemo/djinni/src/)
[info] Executing in batch mode. For better performance use sbt's shell
[info] Updating {file:/Users/javayhu/workspace/github/logindemo/djinni/src/}src...
[info] Resolving org.sonatype.oss#oss-parent;9 ...
[info] downloading https://repo1.maven.org/maven2/org/scala-lang/scala-library/2.11.12/scala-library-2.11.12.jar ...
[info] 	[SUCCESSFUL ] org.scala-lang#scala-library;2.11.12!scala-library.jar (11688ms)
[info] downloading https://repo1.maven.org/maven2/org/scala-lang/modules/scala-parser-combinators_2.11/1.0.1/scala-parser-combinators_2.11-1.0.1.jar ...
[info] 	[SUCCESSFUL ] org.scala-lang.modules#scala-parser-combinators_2.11;1.0.1!scala-parser-combinators_2.11.jar(bundle) (1015ms)
[info] downloading https://repo1.maven.org/maven2/org/yaml/snakeyaml/1.15/snakeyaml-1.15.jar ...
[info] 	[SUCCESSFUL ] org.yaml#snakeyaml;1.15!snakeyaml.jar(bundle) (1031ms)
[info] downloading https://repo1.maven.org/maven2/com/github/scopt/scopt_2.11/3.2.0/scopt_2.11-3.2.0.jar ...
[info] 	[SUCCESSFUL ] com.github.scopt#scopt_2.11;3.2.0!scopt_2.11.jar (841ms)
[info] downloading https://repo1.maven.org/maven2/org/scala-lang/scala-compiler/2.11.12/scala-compiler-2.11.12.jar ...
[info] 	[SUCCESSFUL ] org.scala-lang#scala-compiler;2.11.12!scala-compiler.jar (22251ms)
[info] downloading https://repo1.maven.org/maven2/org/scala-lang/scala-reflect/2.11.12/scala-reflect-2.11.12.jar ...
[info] 	[SUCCESSFUL ] org.scala-lang#scala-reflect;2.11.12!scala-reflect.jar (5000ms)
[info] downloading https://repo1.maven.org/maven2/org/scala-lang/modules/scala-xml_2.11/1.0.5/scala-xml_2.11-1.0.5.jar ...
[info] 	[SUCCESSFUL ] org.scala-lang.modules#scala-xml_2.11;1.0.5!scala-xml_2.11.jar(bundle) (1521ms)
[info] downloading https://repo1.maven.org/maven2/org/scala-lang/modules/scala-parser-combinators_2.11/1.0.4/scala-parser-combinators_2.11-1.0.4.jar ...
[info] 	[SUCCESSFUL ] org.scala-lang.modules#scala-parser-combinators_2.11;1.0.4!scala-parser-combinators_2.11.jar(bundle) (1192ms)
[info] downloading https://repo1.maven.org/maven2/jline/jline/2.14.3/jline-2.14.3.jar ...
[info] 	[SUCCESSFUL ] jline#jline;2.14.3!jline.jar (1002ms)
[info] Done updating.
[info] Compiling 22 Scala sources to /Users/javayhu/workspace/github/logindemo/djinni/src/target/scala-2.11/classes...
[info] 'compiler-interface' not yet compiled for Scala 2.11.12. Compiling...
[info]   Compilation completed in 5.133 s
[success] Total time: 93 s, completed 2020-5-20 11:25:49
[info] Wrote start script for mainClass := Some(djinni.Main) to /Users/javayhu/workspace/github/logindemo/djinni/src/target/start
[success] Total time: 0 s, completed 2020-5-20 11:25:49
```


