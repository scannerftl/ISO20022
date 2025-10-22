#!/bin/sh
# Use this script to wait for a service to be available on a host and port.
# Based on https://github.com/vishnubob/wait-for-it

# Default timeout is 15 seconds
TIMEOUT=15
QUIET=0

# Parse command line arguments
while [ $# -gt 0 ]
do
    case "$1" in
        -h|--help)
            echo "Usage: $0 [-t timeout] host:port [-- command args]"
            echo "  -t, --timeout=timeout      Timeout in seconds, zero for no timeout"
            echo "  -q, --quiet                Don't output any status messages"
            exit 0
            ;;
        -t|--timeout)
            TIMEOUT="$2"
            if [ "$TIMEOUT" = "" ]; then break; fi
            shift 2
            ;;
        -q|--quiet)
            QUIET=1
            shift
            ;;
        --)
            shift
            break
            ;;
        *:* )
            HOST=$(printf "%s\n" "$1"| cut -d : -f 1)
            PORT=$(printf "%s\n" "$1"| cut -d : -f 2)
            shift
            ;;
        *)
            echo "Unknown argument: $1"
            exit 1
            ;;
    esac
done

if [ "$HOST" = "" -o "$PORT" = "" ]; then
    echo "Error: you need to provide a host and port to test." >&2
    exit 1
fi

# Check if timeout is a valid number
if ! [ "$TIMEOUT" -eq "$TIMEOUT" ] 2>/dev/null; then
    echo "Error: invalid timeout '$TIMEOUT'" >&2
    exit 1
fi

# Check if nc is available
if ! command -v nc >/dev/null 2>&1; then
    echo "Error: nc (netcat) is required but not installed" >&2
    exit 1
fi

wait_for() {
    if [ $QUIET -eq 1 ]; then
        nc -z -w 1 "$HOST" "$PORT" >/dev/null 2>&1
    else
        echo "Waiting for $HOST:$PORT..."
        nc -z -w 1 "$HOST" "$PORT" >/dev/null 2>&1
    fi
    
    if [ $? -eq 0 ]; then
        if [ $QUIET -eq 0 ]; then
            echo "$HOST:$PORT is available"
        fi
        return 0
    fi
    return 1
}

# If timeout is 0, just test once and return
exec 3>/dev/null
if [ $QUIET -eq 0 ]; then exec 3>&1; fi

if [ $TIMEOUT -eq 0 ]; then
    wait_for
    RESULT=$?
    if [ $RESULT -ne 0 ]; then
        echo "$HOST:$PORT is not available" >&2
    fi
    exit $RESULT
fi

# Otherwise, wait until the port is available or timeout is reached
TIMER=$TIMEOUT
while [ $TIMER -gt 0 ]; do
    if wait_for; then
        break
    fi
    sleep 1
    TIMER=$((TIMER-1))
done

# Execute the command if the port is available
if [ $TIMER -eq 0 ]; then
    echo "Operation timed out" >&2
    exit 1
else
    if [ $# -gt 0 ]; then
        exec "$@"
    fi
    exit 0
fi
