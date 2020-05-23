➜  account-service git:(master) ✗ bazel build
Starting local Bazel server and connecting to it...
WARNING: Usage: bazel build <options> <targets>.
Invoke `bazel help build` for full description of usage and options.
Your request is correct, but requested an empty set of targets. Nothing will be built.
INFO: SHA256 (https://github.com/stackb/rules_proto/archive/b2913e6340bcbffb46793045ecac928dcf1b34a5.tar.gz) = d456a22a6a8d577499440e8408fc64396486291b570963f7b157f775be11823e
DEBUG: Rule 'build_stack_rules_proto' indicated that a canonical reproducible form can be obtained by modifying arguments sha256 = "d456a22a6a8d577499440e8408fc64396486291b570963f7b157f775be11823e"
DEBUG: Repository build_stack_rules_proto instantiated at:
  no stack (--record_rule_instantiation_callstack not enabled)
Repository rule http_archive defined at:
  /private/var/tmp/_bazel_javayhu/36c924acd6bb23f41d6ccac9bd770377/external/bazel_tools/tools/build_defs/repo/http.bzl:336:16: in <toplevel>
INFO: SHA256 (https://github.com/grpc/grpc/archive/9facfe2b684bbaaf9e75e2d285e7bffceeefe2b1.tar.gz) = dcebd5fe765e2e09d1c683aa185053dfcf82b0bac3791b9c6008d4960e3214fb
DEBUG: Rule 'com_github_grpc_grpc' indicated that a canonical reproducible form can be obtained by modifying arguments sha256 = "dcebd5fe765e2e09d1c683aa185053dfcf82b0bac3791b9c6008d4960e3214fb"
DEBUG: Repository com_github_grpc_grpc instantiated at:
  no stack (--record_rule_instantiation_callstack not enabled)
Repository rule http_archive defined at:
  /private/var/tmp/_bazel_javayhu/36c924acd6bb23f41d6ccac9bd770377/external/bazel_tools/tools/build_defs/repo/http.bzl:336:16: in <toplevel>
INFO: Analyzed 0 targets (0 packages loaded, 0 targets configured).
INFO: Found 0 targets...
INFO: Deleting stale sandbox base /private/var/tmp/_bazel_javayhu/36c924acd6bb23f41d6ccac9bd770377/sandbox
INFO: Elapsed time: 31.733s, Critical Path: 0.02s
INFO: 0 processes.
INFO: Build completed successfully, 1 total action
