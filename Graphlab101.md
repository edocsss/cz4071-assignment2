## Graphlab Build + Make
1. Git clone powergraph
2. Fix outdated links in CMakeLists.txt: `boost`, `libevent`, `gperftools`
3. `./configure` from main graphlab dir. Note for single_node, run `./configure --no-mpi`
4. Create binaries: `cd release/toolkits/graph_analytics && make -j4`. 
Note this step will fail due to `libevent` dependency error.
5. Run autogen.sh for libevent
    1. Edit Makefile.am in test: `vim deps/event/src/libevent/test/Makefile.am`:  
    change TESTS= bla bla to `TESTS=test.sh`
    2. Run autogen.sh
6. ./configure from main graphlab dir. Note for single_node, run `./configure --no-mpi`
7. Create the executables  
`cd release/toolkits/graph_analytics && make -j4`
  
## Running Custom Code (Single Node)
Example of custom code with input: see `bfs` app under single_graphlab/apps (source code) single_graph/release/apps (destination binary + example input file format).  

Note that step 1-3 is already done for page_rank, connected_component and shortest_path packages. So you can straightaway edit the cpp files under these directories.

1. Create new directory package under apps
2. Copy cpp under this package
3. Create CMakeLists.txt for this package  
`project(package_name)`
`add_graphlab_executable(executable_name source_file.cpp)`
4. Cd to home directory of graphlab, run `./configure --no-mpi`.
5. Create the app executable: `cd release/apps/[package_name] && make -j4`
6. Running on single_node: under release/apps/[package_name], run the executable  
    `executable_name --graph=[input_file] --format [input_format]`
    - Note that input_file can be placed in any path
    - For bfs, it can accept `--source arg` input
    - Format can be adjacency list (`adj`), edge list (`tsv`), snap (`snap`).  
    Example: `page_rank --graph=/data/grpNT06s/input.tsv --format tsv`

## Running Custom Code (Distributed)

1. Source setup.sh from main graphlab distributed home
2. Same steps as step 1-3 of Single Node
3. Cd to home directory of graphlab, run `./configure`
4. Same step as step 5 of Single Node
5. Sync the app executables on all the nodes, run `$GRAPHLAB_HOME/scripts/custom-sync.sh`
6. Running on distributed node: under release/apps/[package_name] run the executable via rpc (supposedly by openmpi, but it does not work unfortunately)  
    `python $GRAPHLAB_HOME/scripts/rpc_exec.py -n [num_of_cluster] -f ~/machines executable_name --graph [input_file] --format [input_format]`  
    Example: `python $GRAPHLAB_HOME/scripts/rpcexec.py -n 4 -f ~/machines ./conn_comp --graph input_graph.tsv --format adj`
