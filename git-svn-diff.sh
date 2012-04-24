if [ "$1" == "" ]
then
	echo "First commit is null"
	exit 0
fi

if [ "$2" == "" ]
then
	echo "First commit is null"
	exit 0
fi

# Get SVN revision number for the first commit
REV=`git svn find-rev $1`

if [ "$REV" == "" ]
then
	# if first commit is not on SVN, get highest SVN revision number
	REV=`git svn info | grep Revision | sed -e 's/Revision: //'`
fi

# Then generate the git diff for commits
# and convert to SVN format using the REV fetched above
git diff --no-prefix $1..$2 |
sed -e "/--- \/dev\/null/{ N; s|^--- /dev/null\n+++ \(.*\)|---\1	(revision 0)\n+++\1	(revision 0)|;}" \
    -e "s/^--- .*/&	(revision $REV)/" \
    -e "s/^+++ .*/&	(working copy)/" \
    -e "s/^diff --git [^[:space:]]*/Index:/" \
    -e "s/^index.*/===================================================================/"