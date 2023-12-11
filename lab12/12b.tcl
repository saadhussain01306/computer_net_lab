# b. Implement and study the performance of CDMA on NS2/NS3 
# (Using stack called Call net) or equivalent environment.


#set  parameters 
  set stop 100	;# Stop time. 
  
  # Topology 
  set type umts ;#type of link 
  
  
  # AQM parameters 
  set minth 30 
  set maxth 0 
  set adaptive 1 ;# 1 for Adaptive RED, 0 for plain RED 
  
  
  # Traffic generation. 
  set flows 0 ;# number of long-lived TCP flows 
  set window 30 ;# window for long-lived traffic 
  
  # Plotting statics. 
  set opt(wrap) 100 ;# wrap plots? 
  set opt(srcTrace) is ;# where to plot traffic 
  set opt(dstTrace) bs2 ;# where to plot traffic 
  
  #default downlink bandwidth in bps 
  set bwDL(umts) 384000 
  
  
  #default downlink propagation delay in seconds 
  set propDL(umts) .150 
  
  
  set ns [new Simulator] 
  
  set tf [open Mlab6.tr w] 
  $ns trace-all $tf 
  
  set nodes(is) [$ns node] 
  set nodes(ms) [$ns node] 
  set nodes(bs1) [$ns node] 
  set nodes(bs2) [$ns node] 
  set nodes(lp) [$ns node] 
  
  proc cell_topo {} { 
  global ns nodes 
  $ns duplex-link $nodes(lp) $nodes(bs1) 3Mbps 10ms DropTail 
  $ns duplex-link $nodes(bs1) $nodes(ms) 1 1 RED 
  $ns duplex-link $nodes(ms) $nodes(bs2) 1 1 RED 
  $ns duplex-link $nodes(bs2) $nodes(is) 3Mbps 50ms DropTail 
  puts " umts Cell Topology" 
  } 
  
  proc set_link_para {t} { 
  global ns nodes bwDL propDL 
  $ns bandwidth $nodes(bs1) $nodes(ms) $bwDL($t) duplex 
  $ns bandwidth $nodes(bs2) $nodes(ms) $bwDL($t) duplex 
  
  $ns delay $nodes(bs1) $nodes(ms) $propDL($t) duplex 
  $ns delay $nodes(bs2) $nodes(ms) $propDL($t) duplex 
  
  $ns queue-limit $nodes(bs1) $nodes(ms) 20 
  $ns queue-limit $nodes(bs2) $nodes(ms) 20 
  } 
  
  # RED and TCP parameters 
  Queue/RED set adaptive_ $adaptive 
  Queue/RED set thresh_ $minth 
  Queue/RED set maxthresh_ $maxth 
  Agent/TCP set window_ $window 
  
  #Create topology 
  switch $type { 
  umts {cell_topo} 
  } 
  
  
  set_link_para $type 
  $ns insert-delayer $nodes(ms) $nodes(bs1) [new Delayer] 
  $ns insert-delayer $nodes(ms) $nodes(bs2) [new Delayer] 
  
  # Set up forward TCP connection 
  if {$flows == 0} { 
  set tcp1 [$ns create-connection TCP/Sack1 $nodes(is) TCPSink/Sack1 $nodes(lp) 0] 
  set ftp1 [[set tcp1] attach-app FTP] 
  $ns at 0.8 "[set ftp1] start" 
  } 
  
  
  
  proc stop {} { 
  global nodes opt tf 
  set wrap $opt(wrap) 
  set sid [$nodes($opt(srcTrace)) id] 
  set did [$nodes($opt(dstTrace)) id] 
  
  set a "Mlab6.tr" 
  
  set GETRC "/var/cn/ns-allinone-2.35/ns-2.35/bin/getrc"
  set RAW2XG "/var/cn/ns-allinone-2.35/ns-2.35/bin/raw2xg"
  
  exec $GETRC -s $sid -d $did -f 0 Mlab6.tr | \
  $RAW2XG -s 0.01 -m $wrap -r > plot6.xgr
  
  exec $GETRC -s $did -d $sid -f 0 Mlab6.tr | \
  $RAW2XG -a -s 0.01 -m $wrap >> plot6.xgr
  
  exec xgraph -x time -y packets plot6.xgr & 
  exit 0 
  } 
  $ns at $stop "stop" 
$ns run
